package com.healthcare.cdss.services;

import com.healthcare.cdss.exceptions.AccessDeniedException;
import com.healthcare.cdss.exceptions.CustomException;
import com.healthcare.cdss.exceptions.InvalidRequestException;
import com.healthcare.cdss.entity.Report;
import com.healthcare.cdss.entity.Treatment;

import java.util.List;

public interface CDSSService {
    List<Treatment> getSimilar(String patientId) throws InvalidRequestException;
    String getReport(String patientId) throws CustomException;
    List<Report> getReports(String patientId) throws AccessDeniedException;
}
