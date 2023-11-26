package com.healthcare.medicines.services.factory;

import com.healthcare.medicines.services.Search;
import com.healthcare.medicines.services.strategy.*;

public class SearchFactory {
    private final PatientCallStrategy patientCallStrategy = new PatientCallStrategy();
    private final DoctorCallerStrategy doctorCallerStrategy = new DoctorCallerStrategy();
    private final AppointmentCallerStrategy appointmentCallerStrategy = new AppointmentCallerStrategy();
    private final MedicineCallerStrategy medicineCallerStrategy = new MedicineCallerStrategy();
    private final CommunityCallerStrategy communityCallerStrategy = new CommunityCallerStrategy();

    public Search getInstance(String type){
        if(type.equalsIgnoreCase("patient")) return new PatientSearch(patientCallStrategy);
        else if(type.equalsIgnoreCase("doctor")) return new DoctorSearch(doctorCallerStrategy);
        else if(type.equalsIgnoreCase("community")) return new CommunitySearch(communityCallerStrategy);
        else if(type.equalsIgnoreCase("appointment")) return new AppointmentSearch(appointmentCallerStrategy);
        else if(type.equalsIgnoreCase("medicine")) return new MedicineSearch(medicineCallerStrategy);
        else return null;
    }
}
