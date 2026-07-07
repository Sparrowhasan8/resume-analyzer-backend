package com.Analyze.Resume.analyzer.service;

import com.Analyze.Resume.analyzer.dto.AnalysisDto;
import com.Analyze.Resume.analyzer.repository.AnalysisRepository;
import com.Analyze.Resume.analyzer.util.AnalysisUtil;
import com.Analyze.Resume.entity.Resume;
import com.Analyze.Resume.entity.ResumeAnalysis;
import com.Analyze.Resume.resume.parser.SkillExtractor;
import com.Analyze.Resume.resume.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalysisService {

    private final ResumeRepository resumeRepository;
    private final AnalysisRepository analysisRepository;
    private final SkillExtractor skillExtractor;
    private final AnalysisUtil analysisUtil;


    public AnalysisService(
            ResumeRepository resumeRepository,
            AnalysisRepository analysisRepository,
            SkillExtractor skillExtractor,
            AnalysisUtil analysisUtil) {

        this.resumeRepository = resumeRepository;
        this.analysisRepository = analysisRepository;
        this.skillExtractor = skillExtractor;
        this.analysisUtil = analysisUtil;
    }

    public AnalysisDto analyzeResume(Long resumeId) {

        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() ->
                        new RuntimeException("Resume not found"));

        String resumeText = resume.getExtractedText();

        List<String> skills =
                skillExtractor.extractSkills(resumeText);

        List<String> expectedSkills = List.of(
                "Java",
                "Spring Boot",
                "SQL",
                "Git",
                "REST API",
                "Hibernate",
                "Docker",
                "AWS"
        );

        List<String> matchedSkills = new ArrayList<>();
        List<String> missingSkills = new ArrayList<>();

        for (String skill : expectedSkills) {

            if (skills.contains(skill)) {
                matchedSkills.add(skill);
            } else {
                missingSkills.add(skill);
            }

        }

        int score = analysisUtil.calculateScore(
                matchedSkills.size(),
                expectedSkills.size()
        );

        String suggestions =
                analysisUtil.generateSuggestion(score);
        ResumeAnalysis analysis = new ResumeAnalysis();

        analysis.setResume(resume);
        analysis.setResumeScore(score);
        analysis.setMatchedSkills(String.join(", ", matchedSkills));
        analysis.setMissingSkills(String.join(", ", missingSkills));
        analysis.setSuggestions(suggestions);

        analysisRepository.save(analysis);

        resume.setAnalysis(analysis);
        resumeRepository.save(resume);

        AnalysisDto dto = new AnalysisDto();

        dto.setResumeId(resumeId);
        dto.setResumeScore(score);
        dto.setMatchedSkills(String.join(", ", matchedSkills));
        dto.setMissingSkills(String.join(", ", missingSkills));
        dto.setSuggestions(suggestions);

        return dto;
    }

    public AnalysisDto getAnalysis(Long resumeId) {

        ResumeAnalysis analysis =
                analysisRepository.findByResumeId(resumeId)
                        .orElseThrow(() ->
                                new RuntimeException("Analysis not found"));

        AnalysisDto dto = new AnalysisDto();

        dto.setResumeId(resumeId);
        dto.setResumeScore(analysis.getResumeScore());
        dto.setMatchedSkills(analysis.getMatchedSkills());
        dto.setMissingSkills(analysis.getMissingSkills());
        dto.setSuggestions(analysis.getSuggestions());

        return dto;
    }
}