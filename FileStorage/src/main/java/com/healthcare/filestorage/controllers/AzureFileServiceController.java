package com.healthcare.filestorage.controllers;

import com.healthcare.filestorage.exception.AzureFileHandlingException;
import com.healthcare.filestorage.iservices.AzureFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class AzureFileServiceController {

    private final AzureFileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) throws IOException {
        String response = fileService.upload(file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download")
    public ResponseEntity<String> download(@RequestParam String filePath) throws AzureFileHandlingException {
        fileService.download(filePath);
        return ResponseEntity.ok("Download started successfully.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String url) throws AzureFileHandlingException {
        fileService.delete(url);
        return ResponseEntity.ok("File deleted successfully.");
    }
}
