package com.Analyze.Resume.analyzer.repository;

import com.Analyze.Resume.entity.ResumeAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalysisRepository extends JpaRepository<ResumeAnalysis, Long> {

    Optional<ResumeAnalysis> findByResumeId(Long resumeId);

    @Query("""
        SELECT AVG(a.resumeScore)
        FROM ResumeAnalysis a
        WHERE a.resume.user.id = :userId
    """)
    Double getAverageScoreByUserId(@Param("userId") Long userId);

    @Query("""
        SELECT MAX(a.resumeScore)
        FROM ResumeAnalysis a
        WHERE a.resume.user.id = :userId
    """)
    Integer getHighestScoreByUserId(@Param("userId") Long userId);

}