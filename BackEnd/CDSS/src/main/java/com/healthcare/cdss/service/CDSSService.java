package com.healthcare.cdss.service;

import com.healthcare.cdss.entity.Treatment;
import com.healthcare.cdss.exceptions.InvalidRequestException;

import java.util.List;

public interface CDSSService {
    List<Treatment> getSimilar(String patientId) throws InvalidRequestException;
    String getReport(String patientId);
}
