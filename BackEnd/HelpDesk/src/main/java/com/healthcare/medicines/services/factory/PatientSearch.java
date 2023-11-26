package com.healthcare.medicines.services.factory;

import com.healthcare.medicines.services.Search;
import com.healthcare.medicines.services.strategy.NetworkStrategy;

import java.util.List;
import java.util.Map;

public class PatientSearch implements Search {
    private final NetworkStrategy networkStrategy;

    public PatientSearch(NetworkStrategy networkStrategy){
        this.networkStrategy = networkStrategy;
    }

    @Override
    public List<Map<String, Object>> search(String keywords) {
        return networkStrategy.serviceCall(keywords);
    }
}
