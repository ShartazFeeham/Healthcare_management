package com.healthcare.helpdesk.services.strategy;

import com.healthcare.helpdesk.network.InternalCaller;
import com.healthcare.helpdesk.utilities.constants.Endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MedicineCallerStrategy implements NetworkStrategy {
    private final InternalCaller internalCaller = new InternalCaller(Endpoints.MEDICINES_SEARCH);
    @Override
    public List<Map<String, Object>> serviceCall(String keywords) {
        try{
            Object object = internalCaller.attemptInternalCall(keywords)
                    .block();
            if(object == null) return new ArrayList<>();
            return (List<Map<String, Object>>) object;
        }
        catch (Exception e){
            return new ArrayList<>();
        }
    }
}
