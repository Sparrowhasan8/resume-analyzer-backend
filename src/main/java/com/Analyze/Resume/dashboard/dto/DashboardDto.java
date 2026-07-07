package com.Analyze.Resume.dashboard.dto;

public class DashboardDto {

    private long totalResumes;
    private double averageScore;
    private double highestScore;

    public DashboardDto() {
    }

    public DashboardDto(long totalResumes,
                        double averageScore,
                        double highestScore) {

        this.totalResumes = totalResumes;
        this.averageScore = averageScore;
        this.highestScore = highestScore;
    }

    public long getTotalResumes() {
        return totalResumes;
    }

    public void setTotalResumes(long totalResumes) {
        this.totalResumes = totalResumes;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public double getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(double highestScore) {
        this.highestScore = highestScore;
    }

}