package com.healthcare.filestorage.services;

import com.healthcare.filestorage.exception.LocalFileHandlingException;
import com.healthcare.filestorage.iservices.AzureFileService;
import com.healthcare.filestorage.iservices.LocalFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalFileServiceImpl implements LocalFileService {

    private static final String DOWNLOAD_FOLDER = "downloads";

    @Override
    public String upload(MultipartFile file) throws LocalFileHandlingException {
        try {
            String fileName = UUID.randomUUID() + file.getOriginalFilename();
            Path filePath = getLocalFilePath(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString().replace("\\", "/");
        } catch (IOException e) {
            throw new LocalFileHandlingException("Failed to upload the file: storage permission denied.");
        }
    }

    @Override
    public byte[] download(String filePath) throws LocalFileHandlingException {
        try {
            File file = new File(filePath.replace("/", "\\"));
            if (!file.exists()) {
                throw new LocalFileHandlingException("File not found for download: " + filePath);
            }
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new LocalFileHandlingException("Failed to read the file content: storage permission denied.");
        }
    }

    @Override
    public void delete(String filePath) throws LocalFileHandlingException {
        try {
            File file = new File(filePath.replace("/", "\\"));
            if (!file.exists()) {
                throw new LocalFileHandlingException("File not found for deletion: " + filePath);
            }
            Files.deleteIfExists(file.toPath());
        } catch (IOException e) {
            throw new LocalFileHandlingException("Failed to delete the file: storage permission denied.");
        }
    }

    private Path getLocalFilePath(String fileName) {
        File storageFolder = new File("src/storage");
        if (!storageFolder.exists()) {
            storageFolder.mkdirs();
        }
        return storageFolder.toPath().resolve(fileName).toAbsolutePath();
    }
}
