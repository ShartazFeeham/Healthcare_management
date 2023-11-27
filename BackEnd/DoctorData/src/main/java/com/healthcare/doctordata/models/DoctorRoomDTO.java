package com.healthcare.doctordata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class DoctorRoomDTO {
    private String doctorId;
    private String doctorName;
    private String room;
}
