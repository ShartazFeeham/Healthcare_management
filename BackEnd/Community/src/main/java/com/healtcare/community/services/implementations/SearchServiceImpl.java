package com.healtcare.community.services.implementations;

import com.healtcare.community.entities.Post;
import com.healtcare.community.repositories.PostRepository;
import com.healtcare.community.services.interfaces.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service @RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final PostRepository postRepository;

    @Override
    public List<Map<String, Object>> search(String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();
        Set<String> keywords = stringToSet(keyword);

        var records1 = postRepository.findAll();
        for(var record: records1) {
            Map<String, Object> entity = serach(keywords, record);
            if(entity != null) results.add(entity);
        }

        return results;
    }

    private Map<String, Object> serach(Set<String> words, Post entity){
        int matchValue = 0;
        matchValue += getMatchingWordCount(entity.getTitle(), words, 10);
        matchValue += getMatchingWordCount(entity.getContent(), words, 1);

        if(matchValue > 0){
            Map<String, Object> map = new HashMap<>();
            map.put("photo", entity.getPhotoURL());
            if(entity.getType().equalsIgnoreCase("status") || entity.getType().equalsIgnoreCase("article")){
                map.put("url", "/health/posts/" + entity.getPostId());
            }
            map.put("match", matchValue);
            map.put("type", "community");

            List<String> params = new ArrayList<>();
            List<String> values = new ArrayList<>();

            if(entity.getTitle() != null) {
                params.add("Title: ");
                values.add(entity.getTitle());
            }
            if(entity.getType().equalsIgnoreCase("status") || entity.getType().equalsIgnoreCase("article")
                    || entity.getType().equalsIgnoreCase("firstaid")){
                params.add(null);
                values.add(entity.getContent().substring(0, Math.min(30, entity.getContent().length())) + "...");
                params.add(null);
                values.add("See more at community.");
            }
            else{
                params.add(null);
                values.add(entity.getContent());
            }

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
