package com.healthcare.medicines.services.implemenatations;

import com.healthcare.medicines.entity.Doctor;
import com.healthcare.medicines.models.FilteredBySpecDTO;
import com.healthcare.medicines.repository.DoctorRepository;
import com.healthcare.medicines.services.interfaces.SearchService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service @AllArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final DoctorRepository doctorRepository;

    @Override
    public List<FilteredBySpecDTO> searchBySpecialization(String specialization) {
        if(specialization.equalsIgnoreCase("all"))  return doctorRepository.findAll().stream().map(doc -> {
            return new FilteredBySpecDTO(doc.getUserId(), doc.getFirstName(), doc.getLastName(), doc.getProfilePhoto(), doc.getExperience().toString(), doc.getSpecializations(), doc.getGender());
        }).collect(Collectors.toList());
        return doctorRepository.findBySpecializationsContainingIgnoreCase(specialization).stream().map(doc -> {
            return new FilteredBySpecDTO(doc.getUserId(), doc.getFirstName(), doc.getLastName(), doc.getProfilePhoto(), doc.getExperience().toString(), doc.getSpecializations(), doc.getGender());
        }).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();
        Set<String> keywords = stringToSet(keyword);

        var records1 = doctorRepository.findAll();
        for(var record: records1) {
            Map<String, Object> entity = serach(keywords, record);
            if(entity != null) results.add(entity);
        }

        return results;
    }

    private Map<String, Object> serach(Set<String> words, Doctor entity){
        int matchValue = 0;
        matchValue += getMatchingWordCount(entity.getUserId(), words, 10);
        matchValue += getMatchingWordCount(entity.getSpecializations(), words, 8);
        matchValue += getMatchingWordCount(entity.getFirstName() + " " + entity.getLastName(), words, 5);
        matchValue += getMatchingWordCount(entity.getLicense(), words, 5);
        matchValue += getMatchingWordCount(entity.getQualifications().toString(), words, 3);
        matchValue += getMatchingWordCount(entity.getBio(), words, 1);

        if(matchValue > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("photo", entity.getProfilePhoto());
            map.put("url", "/health/patients/" + entity.getUserId());
            map.put("match", matchValue);
            map.put("type", "doctor");

            List<String> params = new ArrayList<>();
            List<String> values = new ArrayList<>();

            params.add("Doctor ID");
            values.add(entity.getUserId());
            params.add(null);
            values.add(entity.getFirstName() + " " + entity.getLastName());
            params.add(null);
            values.add(prepareDetails(entity.getFirstName(), " has ", entity.getExperience().toString(), " years of experience in ",
                    entity.getSpecializations()));
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
