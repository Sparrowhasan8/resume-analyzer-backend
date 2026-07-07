package com.Analyze.Resume.dashboard.service;

import com.Analyze.Resume.analyzer.repository.AnalysisRepository;
import com.Analyze.Resume.dashboard.dto.DashboardDto;
import com.Analyze.Resume.resume.repository.ResumeRepository;
import com.Analyze.Resume.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final UserService userService;
    private final ResumeRepository resumeRepository;
    private final AnalysisRepository analysisRepository;

    public DashboardService(ResumeRepository resumeRepository,
                            AnalysisRepository analysisRepository, UserService userService) {
        this.resumeRepository = resumeRepository;
        this.analysisRepository = analysisRepository;
        this.userService = userService;
    }

    public DashboardDto getDashboardData() {

        Long userId = userService.getCurrentUser().getId();

        long totalResumes = resumeRepository.countByUserId(userId);

        Double average = analysisRepository.getAverageScoreByUserId(userId);

        Integer highest = analysisRepository.getHighestScoreByUserId(userId);

        if (average == null) {
            average = 0.0;
        }

        if (highest == null) {
            highest = 0;
        }

        return new DashboardDto(
                totalResumes,
                Math.round(average),
                highest
        );
    }
}