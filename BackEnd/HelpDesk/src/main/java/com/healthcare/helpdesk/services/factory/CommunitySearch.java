package com.healthcare.helpdesk.services.factory;

import com.healthcare.helpdesk.services.Search;
import com.healthcare.helpdesk.services.strategy.NetworkStrategy;

import java.util.List;
import java.util.Map;

public class CommunitySearch implements Search {
    private final NetworkStrategy networkStrategy;
    public CommunitySearch(NetworkStrategy networkStrategy){
        this.networkStrategy = networkStrategy;
    }
    @Override
    public List<Map<String, Object>> search(String keywords) {
        return networkStrategy.serviceCall(keywords);
    }
}
