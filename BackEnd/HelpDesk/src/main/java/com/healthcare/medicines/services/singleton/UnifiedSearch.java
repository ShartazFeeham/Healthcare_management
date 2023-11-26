package com.healthcare.medicines.services.singleton;

import com.healthcare.medicines.services.Search;
import com.healthcare.medicines.services.builder.SearchQueryBuilder;
import com.healthcare.medicines.services.factory.SearchFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnifiedSearch {

    // Singleton implementation
    private static UnifiedSearch instance = null;
    private UnifiedSearch(){

    }
    public static UnifiedSearch getInstance(){
        if(instance == null) instance = new UnifiedSearch();
        return instance;
    }

    // Searching instances
    private final Search patientSearch = new SearchFactory().getInstance("patient");
    private final Search doctorSearch = new SearchFactory().getInstance("doctor");
    private final Search medicineSearch = new SearchFactory().getInstance("medicine");
    private final Search appointmentSearch = new SearchFactory().getInstance("appointment");
    private final Search communitySearch = new SearchFactory().getInstance("community");

    public List<SearchQueryBuilder> search(String keywords) {

        List<Map<String, Object>> results = new ArrayList<>();
        results.addAll(safeAdd(patientSearch, keywords));
        results.addAll(safeAdd(doctorSearch, keywords));
        results.addAll(safeAdd(medicineSearch, keywords));
        results.addAll(safeAdd(communitySearch, keywords));
        results.addAll(safeAdd(appointmentSearch, keywords));

        return buildResults(results);
    }

    private List<SearchQueryBuilder> buildResults(List<Map<String, Object>> results){
        return results.stream()
                .map(result -> new SearchQueryBuilder()
                        .url((String) result.getOrDefault("url", ""))
                        .photo((String) result.getOrDefault("photo", ""))
                        .type((String) result.getOrDefault("type", ""))
                        .match((Integer) result.getOrDefault("match", 0))
                        .params((List<String>) result.getOrDefault("params", new ArrayList<>()))
                        .values((List<String>) result.getOrDefault("values", new ArrayList<>())))
                .sorted((result1, result2) -> Integer.compare(result2.getMatch(), result1.getMatch()))
                .collect(Collectors.toList());
    }

    private List<Map<String, Object>> safeAdd(Search searchService, String keywords){
        List<Map<String, Object>> result = new ArrayList<>();
        try{
            return searchService.search(keywords);
        }
        catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
