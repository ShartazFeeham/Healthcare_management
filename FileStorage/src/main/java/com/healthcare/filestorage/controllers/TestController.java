package com.healthcare.filestorage.controllers;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.healthcare.filestorage.utilities.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    BlobContainerClient containerClient;

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        String name = UUID.randomUUID() + file.getOriginalFilename();
        BlobClient blobClient = containerClient.getBlobClient(name);
        blobClient.upload(file.getInputStream(), file.getSize(), true);
        return blobClient.getBlobUrl();
    }

    @GetMapping("/download")
    public void download(@RequestParam("filePath") String filePath) {
        BlobClient blobClient = containerClient.getBlobClient(new File(filePath).getName());
        String userHome = System.getProperty("user.home");
        String destinationPath = userHome + File.separator + "Downloads" + File.separator + new File(filePath).getName();

        blobClient.downloadToFile(destinationPath);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam("url") String url) {
        BlobClient blobClient = containerClient.getBlobClient(new File(url).getName());
        blobClient.delete();
    }
}
