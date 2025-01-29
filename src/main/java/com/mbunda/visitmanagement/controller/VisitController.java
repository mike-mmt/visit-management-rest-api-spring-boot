package com.mbunda.visitmanagement.controller;

import com.mbunda.visitmanagement.domain.Visit;
import com.mbunda.visitmanagement.dto.VisitDto;
import com.mbunda.visitmanagement.dto.VisitSearchCriteria;
import com.mbunda.visitmanagement.service.VisitService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @PostMapping
    public ResponseEntity<VisitDto> createVisit(@RequestBody Visit visit) {
        VisitDto savedVisit = visitService.saveVisit(visit);
        return new ResponseEntity<>(savedVisit, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<VisitDto>> getAllVisits() {
        List<VisitDto> visits = visitService.getAllVisits();
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDto> getVisitById(@PathVariable Long id) {
        return visitService.getVisitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/time")
    public ResponseEntity<List<VisitDto>> getVisitsByTime(@RequestParam LocalDateTime from, @RequestParam LocalDateTime to) {
        return ResponseEntity.ok(visitService.getVisitsFromDatetimeToDatetime(from, to));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<VisitDto>> searchVisits(VisitSearchCriteria criteria) {
        return ResponseEntity.ok(visitService.searchVisits(criteria));
    }

    @GetMapping("/count-by-employee")
    public ResponseEntity<List<Object[]>> getVisitCountByEmployee() {
        return ResponseEntity.ok(visitService.getVisitCountByEmployee());
    }

    @GetMapping("/with-employee-name")
    public ResponseEntity<List<Object[]>> getAllVisitsWithEmployeeName() {
        return ResponseEntity.ok(visitService.getAllVisitsWithEmployeeName());
    }
}
