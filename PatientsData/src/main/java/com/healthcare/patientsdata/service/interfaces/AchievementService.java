package com.healthcare.patientsdata.service.interfaces;

import com.healthcare.patientsdata.entity.Achievement;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;

import java.util.List;

public interface AchievementService {
    public void create(Achievement achievementDTO);
    public Achievement read(Long achievementId) throws ItemNotFoundException;
    public List<Achievement> readAll();
    public void update(Achievement achievementDTO) throws ItemNotFoundException;
    public void delete(Long achievementId) throws ItemNotFoundException;
}
