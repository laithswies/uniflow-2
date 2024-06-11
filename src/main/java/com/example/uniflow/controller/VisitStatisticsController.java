package com.example.uniflow.controller;

import com.example.uniflow.entity.VehicleType;
import com.example.uniflow.entity.VisitStatistics;
import com.example.uniflow.service.VisitStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/visit-statistics")
public class VisitStatisticsController {

    @Autowired
    private VisitStatisticsService service;

    @PostMapping
    public VisitStatistics addOrUpdateVisitStatistics(@RequestBody VisitStatistics visitStatistics) {
        return service.addOrUpdateVisitStatistics(visitStatistics.getVisitDate(), visitStatistics.getVehicleType(), visitStatistics.getCount());
    }

    @GetMapping("/statistics")
    public Map<LocalDate, Map<VehicleType, Long>> getStatisticsByDateRange(@RequestBody Map<String, String> request) {
        String startDateStr = request.get("startDate");
        String endDateStr = request.get("endDate");
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);
        return service.getStatisticsByDateRange(startDate, endDate);
    }


}
