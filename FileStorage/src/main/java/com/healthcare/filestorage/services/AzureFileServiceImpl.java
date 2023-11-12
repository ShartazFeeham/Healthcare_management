package com.healthcare.filestorage.services;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.healthcare.filestorage.exception.AzureFileHandlingException;
import com.healthcare.filestorage.iservices.AzureFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AzureFileServiceImpl implements AzureFileService {

    private final BlobContainerClient containerClient;

    @Override
    public String upload(MultipartFile file) throws AzureFileHandlingException {
        try{
            String name = UUID.randomUUID() + file.getOriginalFilename();
            BlobClient blobClient = containerClient.getBlobClient(name);
            blobClient.upload(file.getInputStream(), file.getSize(), true);
            return blobClient.getBlobUrl();
        }
        catch (Exception e){
            throw new AzureFileHandlingException("Failed to upload the file, possible reason: duplicate file name or server down.");
        }
    }

    @Override
    public void download(String url) throws AzureFileHandlingException {
        try{
            BlobClient blobClient = containerClient.getBlobClient(new File(url).getName());
            String userHome = System.getProperty("user.home");
            String destinationPath = userHome + File.separator + "Downloads" + File.separator + new File(url).getName();

            blobClient.downloadToFile(destinationPath);
        }
        catch (Exception e){
            throw new AzureFileHandlingException("Failed to download the file, possible reason: file already exists in download folder or server down.");
        }
    }

    @Override
    public void delete(String url) throws AzureFileHandlingException {
        try{
            BlobClient blobClient = containerClient.getBlobClient(new File(url).getName());
            blobClient.delete();
        }
        catch (Exception e){
            throw new AzureFileHandlingException("Failed to delete the file, possible reason: file is already deleted or server down.");
        }
    }
}
