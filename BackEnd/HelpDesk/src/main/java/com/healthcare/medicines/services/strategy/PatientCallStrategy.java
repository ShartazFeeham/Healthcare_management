package com.healthcare.medicines.services.strategy;
import com.healthcare.medicines.network.InternalCaller;
import com.healthcare.medicines.utilities.constants.Endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class PatientCallStrategy implements NetworkStrategy {
    private final InternalCaller internalCaller = new InternalCaller(Endpoints.PATIENT_SEARCH);
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
