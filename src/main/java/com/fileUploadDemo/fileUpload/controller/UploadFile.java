package com.fileUploadDemo.fileUpload.controller;

import com.fileUploadDemo.fileUpload.service.UploadFilesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:63342")
public class UploadFile {
    private final UploadFilesService uploadFileService;

    public UploadFile(UploadFilesService uploadFileService) {
        this.uploadFileService = uploadFileService;
    }

    @PostMapping("files")
    public ResponseEntity<String> uploadFile(@RequestBody MultipartFile[] files) throws IOException {
        return uploadFileService.uploadFiles(files);
    }

    @PostMapping("filesWithValidation")
    public ResponseEntity<String> uploadFiles(@RequestParam MultipartFile[] files) {
        return uploadFileService.uploadFilesWithEnhancedValidation(files);
    }
}
