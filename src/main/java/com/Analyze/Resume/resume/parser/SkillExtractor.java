package com.Analyze.Resume.resume.parser;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SkillExtractor {

    private static final List<String> SKILLS = Arrays.asList(

            "Java",
            "Spring",
            "Spring Boot",
            "Hibernate",
            "JPA",
            "REST API",
            "Microservices",
            "MySQL",
            "SQL",
            "PostgreSQL",
            "Oracle",
            "MongoDB",

            "HTML",
            "CSS",
            "JavaScript",
            "TypeScript",
            "React",
            "Angular",
            "Bootstrap",
            "Tailwind",

            "Git",
            "GitHub",
            "Docker",
            "Kubernetes",
            "AWS",
            "Azure",
            "Jenkins",

            "Maven",
            "Gradle",
            "JUnit",
            "Mockito",

            "Python",
            "C",
            "C++",
            "Data Structures",
            "Algorithms"

    );

    public List<String> extractSkills(String resumeText) {

        List<String> extractedSkills = new ArrayList<>();

        if (resumeText == null) {
            return extractedSkills;
        }

        String text = resumeText.toLowerCase();

        for (String skill : SKILLS) {

            if (text.contains(skill.toLowerCase())) {

                extractedSkills.add(skill);

            }
        }

        return extractedSkills;
    }

}