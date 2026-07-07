package com.Analyze.Resume.resume.service;

import com.Analyze.Resume.auth.repository.AuthRepository;
import com.Analyze.Resume.entity.Resume;
import com.Analyze.Resume.entity.User;
import com.Analyze.Resume.resume.dto.ResumeDto;
import com.Analyze.Resume.resume.parser.PdfParserService;
import com.Analyze.Resume.resume.repository.ResumeRepository;
import com.Analyze.Resume.resume.util.FileUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final AuthRepository authRepository;
    private final FileUtil fileUtil;
    private final PdfParserService pdfParserService;

    public ResumeService(ResumeRepository resumeRepository,
                         AuthRepository authRepository,
                         FileUtil fileUtil,
                         PdfParserService pdfParserService) {

        this.resumeRepository = resumeRepository;
        this.authRepository = authRepository;
        this.fileUtil = fileUtil;
        this.pdfParserService = pdfParserService;
    }

    // Upload Resume
    public ResumeDto uploadResume(MultipartFile file) throws IOException {

        if (!fileUtil.isPdf(file)) {
            throw new RuntimeException("Only PDF files are allowed.");
        }

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = authRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String filePath = fileUtil.saveFile(file);

        String extractedText =
                pdfParserService.extractText(filePath);

        Resume resume = new Resume();

        resume.setFileName(file.getOriginalFilename());
        resume.setFilePath(filePath);
        resume.setExtractedText(extractedText);
        resume.setUploadDate(LocalDateTime.now());
        resume.setUser(user);

        Resume savedResume = resumeRepository.save(resume);

        return convertToDto(savedResume);
    }

    // Get Logged-in User Resumes
    public List<ResumeDto> getMyResumes() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = authRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return resumeRepository.findByUserIdOrderByUploadDateDesc(user.getId())
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get Resume By Id
    public ResumeDto getResume(Long id) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = authRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Resume resume = resumeRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        return convertToDto(resume);
    }

    // Delete Resume
    public void deleteResume(Long id) throws IOException {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = authRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Resume resume = resumeRepository
                .findByIdAndUserId(id, user.getId())
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        fileUtil.deleteFile(resume.getFilePath());

        resumeRepository.delete(resume);
    }

    // Convert Entity to DTO
    private ResumeDto convertToDto(Resume resume) {

        ResumeDto dto = new ResumeDto();

        dto.setId(resume.getId());
        dto.setFileName(resume.getFileName());
        dto.setFilePath(resume.getFilePath());
        dto.setExtractedText(resume.getExtractedText());
        dto.setUploadDate(resume.getUploadDate());

        if (resume.getAnalysis() != null) {
            dto.setResumeScore(
                    resume.getAnalysis().getResumeScore()
            );
        }

        return dto;
    }
}