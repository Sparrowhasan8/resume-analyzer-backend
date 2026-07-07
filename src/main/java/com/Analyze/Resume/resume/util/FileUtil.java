package com.Analyze.Resume.resume.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Component
public class FileUtil {

    private static final String UPLOAD_DIR = "uploads";

    public String saveFile(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String originalFileName = file.getOriginalFilename();

        String extension = "";

        if (originalFileName != null &&
                originalFileName.contains(".")) {

            extension = originalFileName.substring(
                    originalFileName.lastIndexOf(".")
            );
        }

        String uniqueFileName =
                UUID.randomUUID() + extension;

        Path filePath =
                uploadPath.resolve(uniqueFileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return filePath.toString();
    }

    public boolean isPdf(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return false;
        }

        String fileName = file.getOriginalFilename();

        return fileName != null &&
                fileName.toLowerCase().endsWith(".pdf");
    }

    public void deleteFile(String filePath)
            throws IOException {

        Files.deleteIfExists(Paths.get(filePath));
    }
}