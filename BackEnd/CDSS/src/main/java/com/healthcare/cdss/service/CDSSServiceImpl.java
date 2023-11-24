package com.healthcare.cdss.service;

import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.repository.TreatmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class CDSSServiceImpl implements CDSSService{

    private final TreatmentRepository treatmentRepository;

    @Override
    public List<Treatment> getSimilar(String patientId) throws InvalidRequestException {
        int batchSize = 10, page = 0;
        List<Treatment> targetTreatments = treatmentRepository.findByPatientId(patientId);
        if (targetTreatments.isEmpty()) {
            throw new InvalidRequestException("Not enough data to run CDSS. Patient must have at least one illness/treatments data to analyze");
        }

        Map<Long, Integer> similarityMap = new HashMap<>();
        while (true) {
            List<Treatment> batch = treatmentRepository.findAll(PageRequest.of(page, batchSize)).stream().toList();
            if (batch.isEmpty()) break;
            for (Treatment treatment : batch) {
                // If its own data then skip
                if(treatment.getPatientId().equals(patientId)) continue;;
                int similarity = targetTreatments.stream()
                        .mapToInt(targetTreatment -> targetTreatment.getSimilarityValue(treatment))
                        .max()
                        .orElse(0);
                similarityMap.put(treatment.getId(), similarity);
            }
            page++;
        }

        // Sort the map by similarity value in descending order
        List<Map.Entry<Long, Integer>> sortedList = similarityMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        // Retrieve the top 2 treatments with the highest similarity
        return sortedList.stream()
                .limit(2)
                .map(entry -> {
                    Treatment treatment = treatmentRepository.findById(entry.getKey()).get();
                    treatment.setPatientId("HIDDEN");
                    treatment.setId(-1L);
                    treatment.setAuthorId("HIDDEN");
                    return treatment;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String getReport(String patientId) {
        return null;
    }
}
