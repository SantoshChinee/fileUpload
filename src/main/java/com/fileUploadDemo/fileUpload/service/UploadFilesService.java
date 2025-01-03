package com.fileUploadDemo.fileUpload.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadFilesService {
    private final Path uploadPath = Paths.get("upload/");
    //Old Code (Spring Boot 1.x):
    public ResponseEntity<String> uploadFiles(MultipartFile[] files) throws IOException {
        String uploadDirectory = "upload/";
        File directory = new File(uploadDirectory);
        if (!directory.exists()) {
            directory.mkdir();
        }
        System.out.println("1");
        String uploadStatus = "";
        for (MultipartFile file : files) {
            Path filePath = Paths.get(uploadDirectory, file.getOriginalFilename());
            Files.write(filePath, file.getBytes());
            uploadStatus = uploadStatus + "Uploaded file " + file.getOriginalFilename() + "\n";
        }
        return ResponseEntity.ok(uploadStatus);
    }
    // New Code (Spring Boot 2.x):
    public ResponseEntity<String> uploadFilesWithEnhancedValidation(MultipartFile[] files) {
        try {
            Path uploadDirectory = Paths.get(System.getProperty("user.dir") + "/uploads");
            Files.createDirectories(uploadDirectory);  // Ensures directory exists

            StringBuilder uploadStatus = new StringBuilder();
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    Path targetPath = uploadDirectory.resolve(file.getOriginalFilename());
                    file.transferTo(targetPath.toFile());  // Safely saves file
                    uploadStatus.append("Uploaded file with validation: ").append(file.getOriginalFilename()).append("\n");
                } else {
                    uploadStatus.append("Skipped empty file.\n");
                }
            }
            return ResponseEntity.ok(uploadStatus.toString());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload files: " + e.getMessage());
        }
    }
}
