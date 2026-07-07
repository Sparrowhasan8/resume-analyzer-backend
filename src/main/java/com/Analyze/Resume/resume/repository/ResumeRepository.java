package com.Analyze.Resume.resume.repository;

import com.Analyze.Resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    long countByUserId(Long userId);

    List<Resume> findByUserIdOrderByUploadDateDesc(Long userId);

    Optional<Resume> findByIdAndUserId(Long id, Long userId);


}