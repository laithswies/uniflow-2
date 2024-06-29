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
    public Map<LocalDate, Map<VehicleType, Long>> getStatisticsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return service.getStatisticsByDateRange(start, end);
    }
}
