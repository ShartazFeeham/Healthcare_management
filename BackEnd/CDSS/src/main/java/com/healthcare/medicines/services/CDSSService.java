package com.healthcare.medicines.services;

import com.healthcare.medicines.entity.Report;
import com.healthcare.medicines.entity.Treatment;
import com.healthcare.medicines.exceptions.AccessDeniedException;
import com.healthcare.medicines.exceptions.CustomException;
import com.healthcare.medicines.exceptions.InvalidRequestException;

import java.util.List;

public interface CDSSService {
    List<Treatment> getSimilar(String patientId) throws InvalidRequestException;
    String getReport(String patientId) throws CustomException;
    List<Report> getReports(String patientId) throws AccessDeniedException;
}
