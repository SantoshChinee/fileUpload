# File Upload Service

This project demonstrates a file upload service built with Spring Boot. It allows users to upload multiple files with enhanced validation and directory management.

## Features
- Supports uploading multiple files.
- Provides basic validation to handle empty files.
- Ensures the upload directory exists before saving files.
- Demonstrates two approaches for file uploads: 
  - Legacy method (Spring Boot 1.x).
  - Enhanced method (Spring Boot 2.x).

## Prerequisites
- Java 8 or later.
- Maven or Gradle installed.
- Spring Boot dependencies included in your project.

## Project Structure
```
com.fileUploadDemo.fileUpload
└── service
    └── UploadFilesService.java
```

## Usage

### 1. Uploading Files Using Legacy Method
The `uploadFiles` method:
- Checks if the upload directory exists and creates it if necessary.
- Writes each file to the specified directory using `Files.write()`.

```java
public ResponseEntity<String> uploadFiles(MultipartFile[] files) throws IOException {
    // Implementation
}
```

### 2. Uploading Files Using Enhanced Validation (Recommended)
The `uploadFilesWithEnhancedValidation` method:
- Ensures the upload directory exists using `Files.createDirectories()`.
- Skips empty files.
- Uses `MultipartFile.transferTo()` for improved file handling.

```java
public ResponseEntity<String> uploadFilesWithEnhancedValidation(MultipartFile[] files) {
    // Implementation
}
```

### Example
Here is an example of how you might call the service in a Spring Boot controller:

```java
@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    @Autowired
    private UploadFilesService uploadFilesService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("files") MultipartFile[] files) throws IOException {
        return uploadFilesService.uploadFilesWithEnhancedValidation(files);
    }
}
```

## File Storage
Uploaded files are stored in the `uploads` directory located in the root of the project. Ensure the application has appropriate permissions to create and write to this directory.

## Error Handling
- Returns a `500 Internal Server Error` if file upload fails.
- Skips files that are empty and provides feedback in the response.

## Running the Application
1. Clone the repository.
2. Build the project using Maven or Gradle:
   ```bash
   mvn clean install
   ```
   or
   ```bash
   gradle build
   ```
3. Run the application:
   ```bash
   java -jar target/your-application.jar
   ```
4. Test the file upload endpoint using a tool like Postman or `curl`:
   ```bash
   curl -X POST -F "files=@yourfile.txt" http://localhost:8080/api/files/upload
   ```

## License
This project is licensed under the MIT License.

## Author
- [Your Name](#)
