package com.healthcare.cdss.repository;

import com.healthcare.cdss.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, String> {
    List<Medicine> findByExpirationDateBefore(LocalDate currentDate);
    List<Medicine> findByExpirationDateAfter(LocalDate currentDate);
}

