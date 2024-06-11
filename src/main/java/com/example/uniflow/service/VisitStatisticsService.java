package com.example.uniflow.service;


import com.example.uniflow.entity.*;
import com.example.uniflow.repository.VisitStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisitStatisticsService {

    @Autowired
    private VisitStatisticsRepository repository;

    @Transactional
    public VisitStatistics addOrUpdateVisitStatistics(LocalDate visitDate,VehicleType vehicleType, long count) {
        VisitStatistics existingRecord = repository.findByVisitDateAndVehicleType(visitDate, vehicleType);
        if (existingRecord != null) {
            existingRecord.setCount(existingRecord.getCount() + count);
            return repository.save(existingRecord);
        } else {
            VisitStatistics newRecord = new VisitStatistics();
            newRecord.setVisitDate(visitDate);
            newRecord.setVehicleType(vehicleType);
            newRecord.setCount(count);
            return repository.save(newRecord);
        }
                }

    public Map<LocalDate, Map<VehicleType, Long>> getStatisticsByDateRange(LocalDate startDate, LocalDate endDate) {
        List<VisitStatistics> statistics = repository.findByVisitDateBetween(startDate, endDate);
        Map<LocalDate, Map<VehicleType, Long>> result = new HashMap<>();

        for (VisitStatistics stat : statistics) {
            result
                    .computeIfAbsent(stat.getVisitDate(), k -> new HashMap<>())
                    .put(stat.getVehicleType(), stat.getCount());
        }

        return result;
    }

}
