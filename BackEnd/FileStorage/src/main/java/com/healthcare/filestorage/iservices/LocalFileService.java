package com.healthcare.filestorage.iservices;

import com.healthcare.filestorage.exception.AzureFileHandlingException;
import com.healthcare.filestorage.exception.LocalFileHandlingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LocalFileService {
    public String upload(MultipartFile file) throws LocalFileHandlingException;
    public byte[] download(String url) throws LocalFileHandlingException;
    public void delete(String url) throws LocalFileHandlingException;
}
