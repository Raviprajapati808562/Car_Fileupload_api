
package com.boot.controller;

import com.boot.service.Fileupload;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/getApiData")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    private final Fileupload fileuploadService;

    public FileUploadController(Fileupload fileuploadService) {
        this.fileuploadService=fileuploadService;
    }

    @PostMapping(
            value = "/uploadFile",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> uploadFile(
            @RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Please select a file.");
        }
        try {
            String fileName = file.getOriginalFilename();
            System.out.println(fileName);
            int count = fileuploadService.uploadFilesIntoDB(file);
            return ResponseEntity.ok(
                    count + " records inserted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to read uploaded file.");
        }
    }
}
