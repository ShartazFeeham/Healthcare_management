package com.healthcare.medicines.service.implemenatations;

import com.healthcare.medicines.entity.Medicine;
import com.healthcare.medicines.enums.FilterTerms;
import com.healthcare.medicines.models.MedicineListResponseDTO;
import com.healthcare.medicines.models.MedicineRequestDTO;
import com.healthcare.medicines.models.MedicineResponseDTO;
import com.healthcare.medicines.repository.MedicineRepository;
import com.healthcare.medicines.service.interfaces.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final MedicineRepository medicineRepository;

    @Override
    public MedicineListResponseDTO filter(String searchTerm, FilterTerms sortType, FilterTerms expFilter, int page, int size) {
        if(searchTerm != null && !searchTerm.isEmpty()) return filterAndSearch(searchTerm, sortType, expFilter);
        else return filterOnly(sortType, expFilter, page, size);
    }

    private MedicineListResponseDTO filterOnly(FilterTerms sortType, FilterTerms expFilter, int page, int size) {
        List<Medicine> medicines;
        if(expFilter == FilterTerms.EXPIRED) medicines = medicineRepository.findByExpirationDateBefore(LocalDate.now());
        else if(expFilter == FilterTerms.NON_EXPIRED) medicines = medicineRepository.findByExpirationDateAfter(LocalDate.now());
        else medicines = medicineRepository.findAll();
        sort(medicines, sortType);
        MedicineListResponseDTO result = convertToResponse(medicines);
        ArrayList<MedicineResponseDTO> list = new ArrayList<>();
        for(int i=page * size; i<Math.min(page * size + size, result.getData().size()); i++) {
            list.add(result.getData().get(i));
        }
        result.setData(list);
        return result;
    }

    private MedicineListResponseDTO filterAndSearch(String searchTerm, FilterTerms sortType, FilterTerms expFilter){
        List<Medicine> medicines = searchMedicines(medicineRepository.findAll(), searchTerm);

        if(expFilter == FilterTerms.EXPIRED) medicines = medicines.stream()
                .filter(m -> m.getExpirationDate().isBefore(LocalDate.now())).toList();
        else if(expFilter == FilterTerms.NON_EXPIRED) medicines = medicines.stream()
                .filter(m -> m.getExpirationDate().isAfter(LocalDate.now())).toList();

        sort(medicines, sortType);
        MedicineListResponseDTO result = convertToResponse(medicines);
        result.setSize(result.getData().size());
        result.setItemsPerPage(result.getData().size());
        return convertToResponse(medicines);
    }

    private void sort(List<Medicine> medicines, FilterTerms sortType){
        if(sortType == FilterTerms.MEDICINE_NAME) medicines.sort(Comparator.comparing(Medicine::getMedicineName));
        else if(sortType == FilterTerms.EXPIRATION_DATE) medicines.sort(Comparator.comparing(Medicine::getExpirationDate));
        else if(sortType == FilterTerms.DOSAGE_FORM) medicines.sort(Comparator.comparing(Medicine::getDosageForm));
    }

    private MedicineListResponseDTO convertToResponse(List<Medicine> medicines){
        MedicineListResponseDTO result = new MedicineListResponseDTO();

        result.setData(medicines.stream().map(m -> {
            return MedicineResponseDTO.builder().medicineId(m.getId())
                    .medicineName(m.getMedicineName()).commercialName(m.getCommercialName())
                    .expirationDate(m.getExpirationDate().toString()).manufacturer(m.getManufacturer())
                    .dosageForm(m.getDosageForm()).strengthWeight(m.getStrengthWeight())
                    .strengthVolume(m.getStrengthVolume()).build();
        }).collect(Collectors.toList()));

        return result;
    }

    private List<Medicine> searchMedicines(List<Medicine> medicines, String searchText) {

        // Searched words set
        Set<String> words = stringToSet(searchText);
        // Use a priority queue to maintain the order based on word matches
        PriorityQueue<Medicine> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(medicine -> getMatchingWordCount(medicine, words)));
        // Iterate through the medicines list and prioritize based on word matches
        for (Medicine medicine : medicines) priorityQueue.offer(medicine);
        // Convert the priority queue to a list and reverse the order to get the highest matching medicines first
        List<Medicine> sortedMedicines = new ArrayList<>(priorityQueue);
        Collections.reverse(sortedMedicines);
        return sortedMedicines;
    }

    private Set<String> stringToSet(String text) {
        String[] splitWords = text.toLowerCase().split("\\s+");
        return new HashSet<>(Arrays.asList(splitWords));
    }


    private int getMatchingWordCount(Medicine medicine, Set<String> searchWords) {
        // Count the number of matching words in medicine properties
        List<String> properties = Arrays.asList(
                medicine.getId(),
                medicine.getCommercialName(), medicine.getMedicineName(), medicine.getClassification(),
                medicine.getDescription(), medicine.getDosageForm(), medicine.getStrengthVolume(),
                medicine.getStrengthWeight(), medicine.getWarnings(), medicine.getAdverseEffects(),
                medicine.getManufacturer(), medicine.getNationalDrugCode(), medicine.getExpirationDate().toString()
        );
        Set<String> medicineText = new HashSet<>();
        for(String propertyText: properties) medicineText.addAll(stringToSet(propertyText));

        int wordMatchCount = 0;
        for(String keyword: searchWords){
            for(String paraText: medicineText){
                if(paraText.contains(keyword)) {
                    wordMatchCount++;
                    break;
                }
            }
        }
        return wordMatchCount;
    }
}