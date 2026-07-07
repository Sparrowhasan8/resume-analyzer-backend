package com.Analyze.Resume.resume.controller;

import com.Analyze.Resume.resume.dto.ResumeDto;
import com.Analyze.Resume.resume.service.ResumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@RestController
@RequestMapping("/api/resume")
@CrossOrigin("*")

public class ResumeController {

    private final ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    // Upload Resume
    @PostMapping("/upload")
    public ResponseEntity<ResumeDto> uploadResume(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        ResumeDto resume =
                resumeService.uploadResume(file);

        return ResponseEntity.ok(resume);
    }

    // Get Logged-in User Resumes
    @GetMapping
    public ResponseEntity<List<ResumeDto>> getMyResumes() {

        return ResponseEntity.ok(
                resumeService.getMyResumes()
        );
    }

    // Get Resume By
    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                resumeService.getResume(id)
        );
    }

    // Delete Resume
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResume(
            @PathVariable Long id)
            throws IOException {

        resumeService.deleteResume(id);

        return ResponseEntity.ok(
                "Resume deleted successfully."
        );
    }

}