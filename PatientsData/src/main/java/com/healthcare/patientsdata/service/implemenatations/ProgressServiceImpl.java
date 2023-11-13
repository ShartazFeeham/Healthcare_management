package com.healthcare.patientsdata.service.implemenatations;

import com.healthcare.patientsdata.entity.Achievement;
import com.healthcare.patientsdata.entity.AchievementProgress;
import com.healthcare.patientsdata.entity.Patient;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.repository.PatientRepository;
import com.healthcare.patientsdata.service.interfaces.AchievementService;
import com.healthcare.patientsdata.service.interfaces.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final PatientRepository patientRepository;
    private final AchievementService achievementService;

    public List<AchievementProgress> readAllByUser(String userId) throws ItemNotFoundException {
        Optional<Patient> patient = patientRepository.findById(userId);
        if(patient.isEmpty()) throw new ItemNotFoundException("patient", userId);
        return patient.get().getAchievementProgresses();
    }

    public void update(String userId, Long achievementId, double score) throws ItemNotFoundException {
        Optional<Patient> patientOp = patientRepository.findById(userId);
        if(patientOp.isEmpty()) throw new ItemNotFoundException("patient", userId);
        Patient patient = patientOp.get();
        for(AchievementProgress achievementProgress: patient.getAchievementProgresses()){
            if(Objects.equals(achievementProgress.getAchievement().getId(), achievementId)){
                addScore(patient, achievementProgress, score);
                return;
            }
        }

        Achievement achievement = achievementService.read(achievementId);
        AchievementProgress newProgress = new AchievementProgress();
        newProgress.setScore(0.0);
        newProgress.setAchievement(achievement);
        newProgress.setChallengeDate(LocalDate.now());
        newProgress.setPatient(patient);
        patient.getAchievementProgresses().add(newProgress);

        addScore(patient, newProgress, score);
    }

    private void addScore(Patient patient, AchievementProgress progress, double score){
        if(progress.getScore() >= progress.getAchievement().getGoalScore()) return;
        progress.setScore(progress.getScore() + score);
        if(progress.getAchievement().getGoalScore() <= progress.getScore()){
            progress.setScore(progress.getAchievement().getGoalScore());
            LocalDate currentDate = LocalDate.now();
            long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(progress.getChallengeDate(), currentDate);
            progress.setCompletedIn((int) daysDifference);
            progress.setCompletionDate(currentDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu")));
        }
    }
}
