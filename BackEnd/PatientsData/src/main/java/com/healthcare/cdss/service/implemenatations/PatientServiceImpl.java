package com.healthcare.cdss.service.implemenatations;
import com.healthcare.cdss.entity.Patient;
import com.healthcare.cdss.exceptions.CustomException;
import com.healthcare.cdss.exceptions.InternalCommunicationException;
import com.healthcare.cdss.exceptions.ItemNotFoundException;
import com.healthcare.cdss.models.*;
import com.healthcare.cdss.network.AccountCreateDTO;
import com.healthcare.cdss.network.AccountCreateRequester;
import com.healthcare.cdss.network.PhoneNoUpdateRequester;
import com.healthcare.cdss.repository.PatientRepository;
import com.healthcare.cdss.service.interfaces.PatientService;
import com.healthcare.cdss.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        bioDTO.setPatientId(patient.getUserId());
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
    public PatientHealthDTO getPatientHealth(String userId) throws CustomException {
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("patient", userId));

        if(!(IDExtractor.getUserID().equals(userId) || IDExtractor.getUserID().startsWith("D") || IDExtractor.getUserID().startsWith("A")))
            throw new CustomException("AccessDeniedException", "You can not see other patients personal healt info.", HttpStatus.BAD_REQUEST);

        PatientHealthDTO healthDTO = new PatientHealthDTO();
        // Set properties from the patient entity
        healthDTO.setPatientId(patient.getUserId());
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

    @Override
    public UserMinimalInfoDTO getUserMinimalInfo(String userId) throws ItemNotFoundException {
        Patient patient = patientRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("patient", userId));
        return UserMinimalInfoDTO.builder().photoURL(patient.getProfilePhoto())
                .firstName(patient.getFirstName()).lastName(patient.getLastName())
                .build();
    }
}