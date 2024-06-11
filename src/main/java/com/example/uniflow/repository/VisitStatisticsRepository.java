package com.example.uniflow.repository;

import com.example.uniflow.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitStatisticsRepository extends JpaRepository<VisitStatistics, Long> {
    List<VisitStatistics> findByVisitDate(LocalDate visitDate);
    VisitStatistics findByVisitDateAndVehicleType(LocalDate visitDate, VehicleType vehicleType);
    List<VisitStatistics> findByVisitDateBetween(LocalDate startDate, LocalDate endDate);
}
