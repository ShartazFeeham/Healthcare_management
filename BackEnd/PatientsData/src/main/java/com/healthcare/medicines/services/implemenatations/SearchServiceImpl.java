package com.healthcare.medicines.services.implemenatations;

import com.healthcare.medicines.entity.Achievement;
import com.healthcare.medicines.entity.Patient;
import com.healthcare.medicines.repository.AchievementRepository;
import com.healthcare.medicines.repository.PatientRepository;
import com.healthcare.medicines.services.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final PatientRepository patientRepository;
    private final AchievementRepository achievementRepository;

    @Override
    public List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();
        Set<String> keywords = stringToSet(keyword);

        var records1 = achievementRepository.findAll();
        for(var record: records1) {
            Map<String, Object> entity = serach(keywords, record);
            if(entity != null) results.add(entity);
        }

        var records2 = patientRepository.findAll();
        for(var record: records2) {
            Map<String, Object> entity = serach(keywords, record);
            if(entity != null) results.add(entity);
        }

        return results;
    }

    private Map<String, Object> serach(Set<String> words, Patient entity){
        int matchValue = 0;
        matchValue += getMatchingWordCount(entity.getUserId(), words, 10);
        matchValue += getMatchingWordCount(entity.getFirstName() + " " + entity.getLastName(), words, 5);
        matchValue += getMatchingWordCount(entity.getBloodGroup(), words, 5);
        matchValue += getMatchingWordCount(entity.getGender(), words, 2);
        matchValue += getMatchingWordCount(entity.getOccupation(), words, 1);
        matchValue += getMatchingWordCount(entity.getAddress(), words, 1);

        if(matchValue > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("photo", entity.getProfilePhoto());
            map.put("url", "/health/patients/" + entity.getUserId());
            map.put("match", matchValue);
            map.put("type", "patient");

            List<String> params = new ArrayList<>();
            List<String> values = new ArrayList<>();

            params.add("Patient ID");
            values.add(entity.getUserId());
            params.add(null);
            values.add(entity.getFirstName() + " " + entity.getLastName());
            params.add(null);
            values.add(prepareDetails(entity.getFirstName(), " from ", entity.getAddress(), " has blood group ",
                    entity.getBloodGroup()));
            params.add(null);
            values.add(" Visit " + entity.getFirstName() + "'s profile to see more details.");

            map.put("params", params);
            map.put("values", values);
            return map;
        }
        return null;
    }

    private String prepareDetails(String ... items){
        String details = items[0];
        for(int i=2; i< items.length; i+=2){
            if(items[i] != null) {
                details += items[i-1] + items[i];
            }
        }
        if(details.equals(items[0])) return "";
        return details;
    }

    private Map<String, Object> serach(Set<String> words, Achievement entity){
        int matchValue = 0;
        matchValue += getMatchingWordCount(entity.getId().toString(), words, 10);
        matchValue += getMatchingWordCount(entity.getTitle(), words, 5);
        matchValue += getMatchingWordCount(entity.getGoalDescription(), words, 1);
        matchValue += getMatchingWordCount(entity.getGoalScore().toString(), words, 1);
        matchValue += getMatchingWordCount(entity.getDifficulty().toString(), words, 1);

        if(matchValue > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("photo", entity.getLogoURL());
            map.put("url", "/health/patient");
            map.put("match", matchValue);
            map.put("type", "achievement");

            List<String> params = new ArrayList<>();
            List<String> values = new ArrayList<>();

            params.add(null);
            values.add(entity.getTitle());
            params.add(null);
            values.add(entity.getGoalDescription());
            params.add("Difficulty");
            values.add(entity.getDifficulty().toString());

            map.put("params", params);
            map.put("values", values);
            return map;
        }
        return null;
    }

    private Set<String> stringToSet(String text) {
        if(text == null) return new HashSet<>();
        String[] splitWords = text.toLowerCase().split("\\s+");
        return new HashSet<>(Arrays.asList(splitWords));
    }

    private int getMatchingWordCount(String text, Set<String> searchWords, int value) {
        Set<String> textWords = stringToSet(text);
        int matchValue = 0;
        for(String keyword: searchWords){
            for(String targetWord: textWords){
                if(targetWord.contains(keyword)) {
                    matchValue += value;
                    break;
                }
            }
        }
        return matchValue;
    }
}
