package com.healthcare.cdss.service.interfaces;

import com.healthcare.cdss.entity.Achievement;
import com.healthcare.cdss.exceptions.ItemNotFoundException;

import java.util.List;

public interface AchievementService {
    public void create(Achievement achievementDTO);
    public Achievement read(Long achievementId) throws ItemNotFoundException;
    public List<Achievement> readAll();
    public void update(Achievement achievementDTO) throws ItemNotFoundException;
    public void delete(Long achievementId) throws ItemNotFoundException;
}
