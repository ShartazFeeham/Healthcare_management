package com.healthcare.patientsdata.service.implemenatations;

import com.healthcare.patientsdata.entity.Certification;
import com.healthcare.patientsdata.entity.Doctor;
import com.healthcare.patientsdata.entity.Qualification;
import com.healthcare.patientsdata.exceptions.AccessMismatchException;
import com.healthcare.patientsdata.exceptions.ItemNotFoundException;
import com.healthcare.patientsdata.models.CreateDoctorAccountDTO;
import com.healthcare.patientsdata.models.ReadDoctorPersonalInfoDTO;
import com.healthcare.patientsdata.models.ReadDoctorProfileDTO;
import com.healthcare.patientsdata.models.UpdateDoctorProfileDTO;
import com.healthcare.patientsdata.network.AccountCreateDTO;
import com.healthcare.patientsdata.network.AccountCreateRequester;
import com.healthcare.patientsdata.repository.DoctorRepository;
import com.healthcare.patientsdata.service.interfaces.DoctorService;
import com.healthcare.patientsdata.utilities.token.IDExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AccountCreateRequester accountCreateRequester;

    @Override
    public void register(CreateDoctorAccountDTO createDoctorAccountDTO) {

        Doctor doctor = new Doctor();
        String id = getId(createDoctorAccountDTO.getFirstName(), createDoctorAccountDTO.getLastName());

        // Mapping fields from DTO to Doctor entity
        doctor.setFirstName(createDoctorAccountDTO.getFirstName());
        doctor.setLastName(createDoctorAccountDTO.getLastName());
        doctor.setEmail(createDoctorAccountDTO.getEmail());
        doctor.setPassword(createDoctorAccountDTO.getPassword());
        doctor.setGender(createDoctorAccountDTO.getGender());
//        doctor.setProfilePhoto(createDoctorAccountDTO.getProfilePhoto());
        doctor.setBio(createDoctorAccountDTO.getBio());
        doctor.setExperience(createDoctorAccountDTO.getExperience());
        doctor.setLicense(createDoctorAccountDTO.getLicense());
        doctor.setSpecializations(createDoctorAccountDTO.getSpecialization());
        doctor.setPhoneNumber(createDoctorAccountDTO.getPhoneNumber());
        doctor.setResidence(createDoctorAccountDTO.getResidence());
        doctor.setDateOfBirth(createDoctorAccountDTO.getDateOfBirth());
        doctor.setNidNo(createDoctorAccountDTO.getNid());

        List<Qualification> qualifications = createDoctorAccountDTO.getQualifications();
        List<Certification> certifications = createDoctorAccountDTO.getCertifications();

        doctor.setQualifications(qualifications);
        doctor.setCertifications(certifications);

        doctorRepository.save(doctor);

        AccountCreateDTO account = new AccountCreateDTO();
        account.setUserId(id);
        account.setEmail(createDoctorAccountDTO.getEmail());
        account.setPassword(createDoctorAccountDTO.getPassword());

        try{
            String result = accountCreateRequester.send(account);
        }
        catch (Exception e){
            doctorRepository.deleteById(id);
            throw e;
        }
    }

    private String getId(String firstName, String lastName) {
        // Generate the initial ID pattern using the first letters of the first and last name
        String idPattern = "P" + firstName.toUpperCase().charAt(0) + lastName.toUpperCase().charAt(0);
        long count = doctorRepository.countByUserIdStartingWith(idPattern) + 1;
        return  idPattern + count;
    }


    @Override
    public ReadDoctorPersonalInfoDTO readPersonalInfo(String userId) throws ItemNotFoundException {
        Doctor doctor = doctorRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Doctor", userId));

        ReadDoctorPersonalInfoDTO personalInfoDTO = new ReadDoctorPersonalInfoDTO();

        // Mapping fields from Doctor entity to ReadDoctorPersonalInfoDTO
        personalInfoDTO.setPhone(doctor.getPhoneNumber());
        personalInfoDTO.setDateOfBirth(doctor.getDateOfBirth());
        personalInfoDTO.setNidNo(doctor.getNidNo());
        personalInfoDTO.setResidence(doctor.getResidence());

        return personalInfoDTO;
    }

    @Override
    public ReadDoctorProfileDTO readDoctorProfileInfo(String userId) throws ItemNotFoundException {
        Doctor doctor = doctorRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Doctor", userId));

        ReadDoctorProfileDTO profileDTO = new ReadDoctorProfileDTO();

        // Mapping fields from Doctor entity to ReadDoctorProfileDTO
        profileDTO.setDoctorId(doctor.getDoctorId());
        profileDTO.setFirstName(doctor.getFirstName());
        profileDTO.setLastName(doctor.getLastName());
        profileDTO.setEmail(doctor.getEmail());
        profileDTO.setGender(doctor.getGender());
        profileDTO.setProfilePhoto(doctor.getProfilePhoto());
        profileDTO.setBio(doctor.getBio());
        profileDTO.setExperience(doctor.getExperience());
        profileDTO.setLicense(doctor.getLicense());
        profileDTO.setSpecializations(doctor.getSpecializations());
        profileDTO.setQualifications(doctor.getQualifications());
        profileDTO.setCertifications(doctor.getCertifications());

        return profileDTO;
    }


    @Override
    public void update(String userId, UpdateDoctorProfileDTO updateDoctorProfileDTO) throws AccessMismatchException, ItemNotFoundException {
        Doctor doctor = doctorRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Doctor", userId));

        if(!userId.equals(IDExtractor.getUserID())) throw new AccessMismatchException("You don't have permission to edit others' profile info.");

        // Update doctor fields based on the DTO
        doctor.setFirstName(updateDoctorProfileDTO.getFirstName());
        doctor.setLastName(updateDoctorProfileDTO.getLastName());
        doctor.setPhoneNumber(updateDoctorProfileDTO.getPhoneNumber());
        doctor.setSpecializations(updateDoctorProfileDTO.getSpecialization());
        doctor.setResidence(updateDoctorProfileDTO.getResidence());
        doctor.setBio(updateDoctorProfileDTO.getBio());
        doctor.setExperience(updateDoctorProfileDTO.getExperience());
        //doctor.setPhoto(updateDoctorProfileDTO.getPhoto());
        doctor.setQualifications(updateDoctorProfileDTO.getQualifications());
        doctor.setCertifications(updateDoctorProfileDTO.getCertifications());

        doctorRepository.save(doctor);
    }

    @Override
    public void delete(String userId) throws ItemNotFoundException {
        Doctor doctor = doctorRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFoundException("Doctor", userId));
        doctorRepository.deleteById(userId);
    }
}
