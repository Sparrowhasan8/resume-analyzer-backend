package com.Analyze.Resume.dashboard.controller;

import com.Analyze.Resume.dashboard.dto.DashboardDto;
import com.Analyze.Resume.dashboard.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin("*")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<DashboardDto> getDashboard() {

        return ResponseEntity.ok(
                dashboardService.getDashboardData()
        );

    }

}