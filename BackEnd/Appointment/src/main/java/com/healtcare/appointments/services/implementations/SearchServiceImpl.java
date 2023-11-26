package com.healtcare.appointments.services.implementations;

import com.healtcare.appointments.entities.Equipment;
import com.healtcare.appointments.repositories.EquipmentRepository;
import com.healtcare.appointments.services.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final EquipmentRepository medicineRepository;

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

    private Map<String, Object> serach(Set<String> words, Equipment entity){
        int matchValue = 0;
        matchValue += getMatchingWordCount(entity.getId(), words, 10);
        matchValue += getMatchingWordCount(entity.getName(), words, 5);
        matchValue += getMatchingWordCount(entity.getDetails(), words, 1);
        matchValue += getMatchingWordCount(entity.getUseCases(), words, 1);
        matchValue += getMatchingWordCount(entity.getCosting(), words, 1);

        if(matchValue > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("photo", entity.getPhotoURL());
            map.put("url", "/common/equipments/" + entity.getId());
            map.put("match", matchValue);
            map.put("type", "equipment");

            List<String> params = new ArrayList<>();
            List<String> values = new ArrayList<>();

            params.add("Equipment ID");
            values.add(entity.getId());
            params.add(null);
            values.add(entity.getName());
            params.add("Availability: ");
            values.add(entity.getAvailability());
            params.add("Costing: ");
            values.add(entity.getCosting());
            params.add(null);
            values.add(entity.getDetails().substring(0, Math.min(entity.getDetails().length(), 30)) + "...");
            params.add(null);
            values.add("See more in details");

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
