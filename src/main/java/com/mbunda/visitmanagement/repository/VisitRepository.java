package com.mbunda.visitmanagement.repository;

import com.mbunda.visitmanagement.domain.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findAllByDateAfterAndDateBefore(LocalDateTime from, LocalDateTime to);

    Page<Visit> findByDateBetweenAndEmployeeIdAndServiceIdAndClientId(
            LocalDateTime dateFrom, LocalDateTime dateTo,
            Long employeeId, Long serviceId, Long clientId,
            Pageable pageable);

    Page<Visit> findByDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo, Pageable pageable);

    Page<Visit> findByEmployeeId(Long employeeId, Pageable pageable);

    Page<Visit> findByServiceId(Long serviceId, Pageable pageable);

    Page<Visit> findByClientId(Long clientId, Pageable pageable);

    @Query("SELECT NEW com.mbunda.visitmanagement.dto.EmployeeVisitCountDto(e.name, COUNT(v)) FROM Visit v JOIN v.employee e GROUP BY e.name")
    List<Object[]> countVisitsByEmployee();

    @Query(value = "SELECT v.id, v.date, e.name AS employee_name FROM visit v JOIN employee e ON v.employee_id = e.id", nativeQuery = true)
    List<Object[]> findAllVisitsWithEmployeeName();
}