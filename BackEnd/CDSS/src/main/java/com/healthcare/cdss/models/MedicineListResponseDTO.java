package com.healthcare.cdss.models;

import java.util.List;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class MedicineListResponseDTO {
    private int size;
    private int itemsPerPage;
    private List<MedicineResponseDTO> data;
}
