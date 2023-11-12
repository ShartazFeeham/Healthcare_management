package com.healthcare.filestorage.iservices;

import com.healthcare.filestorage.exception.AzureFileHandlingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AzureFileService {
    public String upload(MultipartFile file) throws IOException;
    public void download(String url) throws AzureFileHandlingException;
    public void delete(String url) throws AzureFileHandlingException;
}
