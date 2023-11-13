package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.Achievement;

import java.util.List;

public interface AchievementService {
    public void create(Achievement achievementDTO);
    public Achievement read(Long achievementId);
    public List<Achievement> readAll();
    public void update(Achievement achievementDTO);
    public void delete(Long achievementId);
}
