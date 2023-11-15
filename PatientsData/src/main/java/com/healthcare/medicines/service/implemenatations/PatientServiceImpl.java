package com.healthcare.medicines.service.implemenatations;
import com.healthcare.medicines.entity.Patient;
import com.healthcare.medicines.exceptions.InternalCommunicationException;
import com.healthcare.medicines.exceptions.ItemNotFoundException;
import com.healthcare.medicines.models.PatientBioDTO;
import com.healthcare.medicines.models.PatientHealthDTO;
import com.healthcare.medicines.models.PatientProfileUpdateDTO;
import com.healthcare.medicines.models.PatientRegisterDTO;
import com.healthcare.medicines.network.AccountCreateDTO;
import com.healthcare.medicines.network.AccountCreateRequester;
import com.healthcare.medicines.network.PhoneNoUpdateRequester;
import com.healthcare.medicines.repository.PatientRepository;
import com.healthcare.medicines.service.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final AccountCreateRequester accountCreateRequester;
    private final PhoneNoUpdateRequester phoneNoUpdateRequester;

    @Override
    public void register(PatientRegisterDTO patientRegisterDTO) throws InternalCommunicationException {

        String id = getId(patientRegisterDTO.getFirstName(), patientRegisterDTO.getLastName());
        Patient patient = new Patient();
        patient.setUserId(id);

        patient.setFirstName(patientRegisterDTO.getFirstName());
        patient.setLastName(patientRegisterDTO.getLastName());
        patient.setAge(patientRegisterDTO.getAge());
        patient.setGender(patientRegisterDTO.getGender());
        patient.setEmail(patientRegisterDTO.getEmail());

        AccountCreateDTO account = new AccountCreateDTO();
        account.setUserId(id);
        account.setEmail(patientRegisterDTO.getEmail());
        account.setPassword(patientRegisterDTO.getPassword());

        patientRepository.save(patient);
        try{
            String result = accountCreateRequester.send(account);
        }
        catch (Exception e){
            patientRepository.deleteById(id);
            throw e;
        }
    }

    private String getId(String firstName, String lastName) {
        // Generate the initial ID pattern using the first letters of the first and last name
        String idPattern = "P" + firstName.toUpperCase().charAt(0) + lastName.toUpperCase().charAt(0);
        long count = patientRepository.countByUserIdStartingWith(idPattern) + 1;
        return  idPattern + count;
    }


    @Override
    public PatientBioDTO getPatientBio(String userId) throws ItemNotFoundException {
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("patient", userId));

        PatientBioDTO bioDTO = new PatientBioDTO();
        // Set properties from the patient entity
        bioDTO.setFirstName(patient.getFirstName());
        bioDTO.setLastName(patient.getLastName());
        bioDTO.setEmail(patient.getEmail());
        bioDTO.setPhone(patient.getPhone());
        bioDTO.setAddress(patient.getAddress());
        bioDTO.setGender(patient.getGender());
        bioDTO.setBloodGroup(patient.getBloodGroup());
        bioDTO.setProfilePhoto(patient.getProfilePhoto());

        return bioDTO;
    }

    @Override
    public PatientHealthDTO getPatientHealth(String userId) throws ItemNotFoundException {
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("patient", userId));

        PatientHealthDTO healthDTO = new PatientHealthDTO();
        // Set properties from the patient entity
        healthDTO.setWeight(patient.getWeight());
        healthDTO.setAge(patient.getAge());
        healthDTO.setBloodPressure(patient.getBloodPressure());
        healthDTO.setDiabetes(patient.getDiabetes());
        healthDTO.setHeight(patient.getHeight());
        healthDTO.setAllergies(patient.getAllergies());
        healthDTO.setOccupation(patient.getOccupation());
        healthDTO.setAsthma(boolToString(patient.isAsthma()));
        healthDTO.setSmoking(boolToString(patient.isSmoking()));
        healthDTO.setDrinking(boolToString(patient.isDrinking()));

        return healthDTO;
    }

    private String boolToString(boolean status){
        return status ? "Yes" : "No";
    }

    @Override
    public void updatePatientProfile(String userId, PatientProfileUpdateDTO request) throws ItemNotFoundException {
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("patient", userId));

        // Update properties from the request DTO
        patient.setFirstName(request.getFirstName());
        patient.setLastName(request.getLastName());
        patient.setGender(request.getGender());
        patient.setProfilePhoto(request.getProfilePhoto());
        patient.setAllergies(request.getAllergies());
        patient.setAge(request.getAge());
        patient.setHeight(request.getHeight());
        patient.setWeight(request.getWeight());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setDiabetes(request.getBloodSugar());
        patient.setBloodPressure(request.getBloodPressure());
        patient.setOccupation(request.getOccupation());
        patient.setPhone(request.getPhoneNo());
        patient.setAddress(request.getResidence());
        patient.setSmoking(request.isSmoking());
        patient.setDrinking(request.isDrinking());
        patient.setAsthma(request.isAsthma());

        patientRepository.save(patient);

        phoneNoUpdateRequester.send(patient.getUserId(), patient.getPhone());
    }
}