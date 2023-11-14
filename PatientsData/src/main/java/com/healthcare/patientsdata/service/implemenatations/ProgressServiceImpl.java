package com.healthcare.patientsdata.service.implemenatations;

import com.healthcare.patientsdata.entity.Achievement;
import com.healthcare.patientsdata.entity.AchievementProgress;
import com.healthcare.patientsdata.entity.Patient;
import com.healthcare.patientsdata.entity.Score;
import com.healthcare.patientsdata.exceptions.AccessMismatchException;
import com.healthcare.patientsdata.exceptions.CustomException;
import com.healthcare.patientsdata.exceptions.DuplicateEntityException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.repository.PatientRepository;
import com.healthcare.patientsdata.repository.ProgressRepository;
import com.healthcare.patientsdata.repository.ScoreRepository;
import com.healthcare.patientsdata.service.interfaces.AchievementService;
import com.healthcare.patientsdata.service.interfaces.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final PatientRepository patientRepository;
    private final AchievementService achievementService;
    private final ProgressRepository progressRepository;
    private final ScoreRepository scoreRepository;

    public List<AchievementProgress> readAllByUser(String userId) throws ItemNotFoundException {
        Optional<Patient> patient = patientRepository.findById(userId);
        if (patient.isEmpty()) throw new ItemNotFoundException("patient", userId);
        List<AchievementProgress> achievementProgresses = patient.get().getAchievementProgresses();

        return achievementProgresses.stream()
                .sorted(Comparator.comparingDouble(this::calculateCompletionPercentage)
                        .reversed()).toList();
    }

    private double calculateCompletionPercentage(AchievementProgress progress) {
        return progress.getScores().stream()
                .mapToDouble(Score::getPoint).sum() /
                (double) progress.getAchievement().getGoalScore();
    }

    @Override
    public void acceptChallenge(Long achievementId) throws ItemNotFoundException, DuplicateEntityException {
        String userId = "PSF1";

        Achievement achievement = achievementService.read(achievementId);
        Optional<Patient> patientOp = patientRepository.findById(userId);
        if(patientOp.isEmpty()) throw new ItemNotFoundException("patient", userId);
        Patient patient = patientOp.get();

        List<AchievementProgress> progresses = patient.getAchievementProgresses();
        if(!progresses.stream().filter(p -> p.getAchievement().getId().equals(achievementId)).toList().isEmpty()){
            throw new DuplicateEntityException("achievement challange", achievementId.toString());
        }

        AchievementProgress newProgress = new AchievementProgress();
        newProgress.setScores(new ArrayList<>());
        newProgress.setAchievement(achievement);
        newProgress.setChallengeDate(LocalDate.now());
        newProgress.setPatient(patient);
        patient.getAchievementProgresses().add(newProgress);

        patientRepository.save(patient);
    }

    @Override
    public void cancelChallenge(Long progressId) throws ItemNotFoundException, AccessMismatchException {
        String userId = "PSF1";

        Optional<Patient> patientOp = patientRepository.findById(userId);
        if (patientOp.isEmpty()) throw new ItemNotFoundException("patient", userId);

        AchievementProgress progress = progressRepository.findById(progressId).orElseThrow(() -> new ItemNotFoundException("achievement progress", progressId.toString()));
        if(!progress.getPatient().getUserId().equals(userId))
            throw new AccessMismatchException("Current user doesn't own the achievement progress.");

        progress.setAchievement(null);

        Iterator<Score> iterator = progress.getScores().iterator();
        while (iterator.hasNext()) {
            Score score = iterator.next();
            iterator.remove();
            scoreRepository.delete(score);
        }

        progressRepository.delete(progress);
    }


    @Override
    public String addScore(Long progressId, double score, LocalDate date) throws ItemNotFoundException {
        Optional<AchievementProgress> progressOptional = progressRepository.findById(progressId);
        if (progressOptional.isEmpty()) {
            throw new ItemNotFoundException("achievement challenge", progressId.toString());
        }

        AchievementProgress progress = progressOptional.get();
        List<Score> scores = progress.getScores();

        Score newScore = new Score();
        newScore.setPoint(score);
        newScore.setDate(date);
        newScore.setProgress(progress);
        scores.add(newScore);

        // Update & save progress details
        boolean congratulations = updateProgressDetails(progress);

        progressRepository.save(progress);

        if(congratulations) return "Congratulations! Achievement completed successfully.";
        else return "Score successfully added.";
    }

    @Override
    public void deleteScore(Long scoreId) throws ItemNotFoundException {
        Optional<Score> scoreOptional = scoreRepository.findById(scoreId);
        if (scoreOptional.isEmpty()) {
            throw new ItemNotFoundException("achievement progress score", scoreId.toString());
        }

        Score score = scoreOptional.get();
        AchievementProgress progress = score.getProgress();
        progress.getScores().remove(score);

        // Update & save progress details
        updateProgressDetails(progress);
        scoreRepository.delete(score);
        progressRepository.save(progress);
    }

    private boolean updateProgressDetails(AchievementProgress progress) {
        double totalScore = progress.getScores().stream().mapToDouble(Score::getPoint).sum();
        progress.setTotalScore(totalScore);

        if (totalScore >= progress.getAchievement().getGoalScore()
                && progress.getCompletionDate() == null) {
            LocalDate currentDate = LocalDate.now();
            long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(progress.getChallengeDate(), currentDate);
            progress.setCompletedIn((int) daysDifference);
            progress.setCompletionDate(currentDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu")));

            return true;
        }
        return false;
    }
}
