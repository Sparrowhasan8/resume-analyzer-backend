package com.Analyze.Resume.resume.dto;

import java.time.LocalDateTime;

public class ResumeDto {

    private Long id;
    private String fileName;
    private String filePath;
    private String extractedText;
    private Integer resumeScore;
    private LocalDateTime uploadDate;

    public ResumeDto() {
    }

    public ResumeDto(Long id, String fileName, String filePath,
                     String extractedText, Integer resumeScore,
                     LocalDateTime uploadDate) {
        this.id = id;
        this.fileName = fileName;
        this.filePath = filePath;
        this.extractedText = extractedText;
        this.resumeScore = resumeScore;
        this.uploadDate = uploadDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getExtractedText() {
        return extractedText;
    }

    public void setExtractedText(String extractedText) {
        this.extractedText = extractedText;
    }

    public Integer getResumeScore() {
        return resumeScore;
    }

    public void setResumeScore(Integer resumeScore) {
        this.resumeScore = resumeScore;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}