package com.healthcare.medicines.services.implemenatations;

import com.healthcare.medicines.entity.Medicine;
import com.healthcare.medicines.repository.MedicineRepository;
import com.healthcare.medicines.services.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final MedicineRepository medicineRepository;

    @Override
    public List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();
        Set<String> keywords = stringToSet(keyword);

        var records1 = medicineRepository.findAll();
        for(var record: records1) {
            Map<String, Object> entity = serach(keywords, record);
            if(entity != null) results.add(entity);
        }

        return results;
    }

    private Map<String, Object> serach(Set<String> words, Medicine entity){
        int matchValue = 0;
        matchValue += getMatchingWordCount(entity.getId(), words, 10);
        matchValue += getMatchingWordCount(entity.getMedicineName() + " " + entity.getCommercialName(), words, 5);
        matchValue += getMatchingWordCount(entity.getDosageForm(), words, 2);
        matchValue += getMatchingWordCount(entity.getManufacturer(), words, 5);
        matchValue += getMatchingWordCount(entity.getNationalDrugCode(), words, 5);
        matchValue += getMatchingWordCount(entity.getDescription() + entity.getWarnings(), words, 1);

        if(matchValue > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("photo", entity.getPhotoUrl());
            map.put("url", "/common/medicines/" + entity.getId());
            map.put("match", matchValue);
            map.put("type", "medicine");

            List<String> params = new ArrayList<>();
            List<String> values = new ArrayList<>();

            params.add("Medicine ID");
            values.add(entity.getId());
            params.add(null);
            values.add(entity.getCommercialName());
            params.add("Medicine name: ");
            values.add(entity.getMedicineName());
            params.add(null);
            values.add(prepareDetails(entity.getMedicineName(), " is a ", entity.getDosageForm(),
                    " manufactured by ", entity.getManufacturer()));
            params.add(null);
            values.add("Click to see details, warnings and other information.");

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
