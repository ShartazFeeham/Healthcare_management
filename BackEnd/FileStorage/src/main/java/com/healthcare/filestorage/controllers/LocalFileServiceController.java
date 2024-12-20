package com.healthcare.filestorage.controllers;

import com.healthcare.filestorage.exception.AzureFileHandlingException;
import com.healthcare.filestorage.exception.LocalFileHandlingException;
import com.healthcare.filestorage.iservices.AzureFileService;
import com.healthcare.filestorage.iservices.LocalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/v2")
@RequiredArgsConstructor
public class LocalFileServiceController {

    private final LocalFileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) throws LocalFileHandlingException {
        String response = fileService.upload(file);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/download")
    public ResponseEntity<Object> download(@RequestParam String filePath) throws LocalFileHandlingException {
        Object object = fileService.download(filePath);
        return ResponseEntity.ok(object);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestParam String url) throws LocalFileHandlingException {
        fileService.delete(url);
        return ResponseEntity.ok("File deleted successfully.");
    }
}
