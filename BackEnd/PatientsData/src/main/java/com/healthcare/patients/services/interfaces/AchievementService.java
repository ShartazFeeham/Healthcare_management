package com.healthcare.patients.services.interfaces;

import com.healthcare.patients.entity.Achievement;
import com.healthcare.patients.exceptions.ItemNotFoundException;

import java.util.List;

public interface AchievementService {
    public void create(Achievement achievementDTO);
    public Achievement read(Long achievementId) throws ItemNotFoundException;
    public List<Achievement> readAll();
    public void update(Achievement achievementDTO) throws ItemNotFoundException;
    public void delete(Long achievementId) throws ItemNotFoundException;
}
