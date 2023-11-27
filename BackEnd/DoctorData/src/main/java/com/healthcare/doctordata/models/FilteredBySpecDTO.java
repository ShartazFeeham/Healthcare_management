package com.healthcare.doctordata.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class FilteredBySpecDTO {
    String userId;
    String firstName;
    String lastName;
    String photoURL;
    String experience;
    String specializations;
    String gender;
}
