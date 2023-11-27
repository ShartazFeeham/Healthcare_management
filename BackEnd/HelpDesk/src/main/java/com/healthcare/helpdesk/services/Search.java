package com.healthcare.helpdesk.services;

import java.util.List;
import java.util.Map;

public interface Search {
    public List<Map<String, Object>> search(String keywords);
}
