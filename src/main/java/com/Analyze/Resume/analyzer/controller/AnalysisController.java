package com.Analyze.Resume.analyzer.controller;

import com.Analyze.Resume.analyzer.dto.AnalysisDto;
import com.Analyze.Resume.analyzer.service.AnalysisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analysis")
@CrossOrigin("*")
public class AnalysisController {

    private final AnalysisService analysisService;

    public AnalysisController(AnalysisService analysisService) {
        this.analysisService = analysisService;
    }

    // Analyze Resume
    @PostMapping("/{resumeId}")
    public ResponseEntity<AnalysisDto> analyzeResume(
            @PathVariable Long resumeId) {

        return ResponseEntity.ok(
                analysisService.analyzeResume(resumeId)
        );
    }

    // Get Analysis
    @GetMapping("/{resumeId}")
    public ResponseEntity<AnalysisDto> getAnalysis(
            @PathVariable Long resumeId) {

        return ResponseEntity.ok(
                analysisService.getAnalysis(resumeId)
        );
    }

}