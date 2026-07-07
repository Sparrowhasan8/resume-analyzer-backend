package com.Analyze.Resume.analyzer.util;

import org.springframework.stereotype.Component;

@Component
public class AnalysisUtil {

    public int calculateScore(int matchedSkills,
                              int totalSkills) {

        if (totalSkills == 0) {
            return 0;
        }

        return (matchedSkills * 100) / totalSkills;
    }

    public String generateSuggestion(int score) {

        if (score >= 90) {
            return "Excellent Resume. Ready to apply.";
        }

        if (score >= 75) {
            return "Good Resume. Add a few more relevant skills.";
        }

        if (score >= 50) {
            return "Average Resume. Improve skills and add projects.";
        }

        return "Poor Resume. Add technical skills, projects and certifications.";
    }

}