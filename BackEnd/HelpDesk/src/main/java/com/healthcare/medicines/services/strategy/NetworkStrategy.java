package com.healthcare.medicines.services.strategy;

import java.util.List;
import java.util.Map;

public interface NetworkStrategy {
    List<Map<String, Object>> serviceCall(String keywords);
}
