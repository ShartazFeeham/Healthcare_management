package com.healtcare.appointments.services.interfaces;

import java.util.List;
import java.util.Map;

public interface SearchService {
    List<Map<String, Object>> search(String keyword);
}