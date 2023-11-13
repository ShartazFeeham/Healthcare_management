package com.healthcare.patientsdata.service.implemenatations;

import com.healthcare.patientsdata.entity.Achievement;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.repository.AchievementRepository;
import com.healthcare.patientsdata.service.interfaces.AchievementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AchievementServiceImpl implements AchievementService {

    private final AchievementRepository achievementRepository;

    @Override
    public void create(Achievement achievementDTO) {
        achievementRepository.save(achievementDTO);
    }

    @Override
    public Achievement read(Long achievementId) throws ItemNotFoundException {
        Optional<Achievement> achievementOptional = achievementRepository.findById(achievementId);
        if (achievementOptional.isEmpty()) {
            throw new ItemNotFoundException("achievement", achievementId.toString());
        }
        return achievementOptional.get();
    }

    @Override
    public List<Achievement> readAll() {
        return achievementRepository.findAll();
    }

    @Override
    public void update(Achievement achievementDTO) throws ItemNotFoundException {
        read(achievementDTO.getId());
        achievementRepository.save(achievementDTO);
    }

    @Override
    public void delete(Long achievementId) throws ItemNotFoundException {
        if (!achievementRepository.existsById(achievementId)) {
            throw new ItemNotFoundException("achievement", achievementId.toString());
        }
        achievementRepository.deleteById(achievementId);
    }
}
