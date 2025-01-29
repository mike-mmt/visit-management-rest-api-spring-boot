package com.mbunda.visitmanagement.service;

import com.mbunda.visitmanagement.domain.Visit;
import com.mbunda.visitmanagement.dto.VisitDto;
import com.mbunda.visitmanagement.dto.VisitSearchCriteria;
import com.mbunda.visitmanagement.mapper.EntityMapper;
import com.mbunda.visitmanagement.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitService {
    private final VisitRepository visitRepository;
    private final EntityMapper entityMapper;

    public VisitService(VisitRepository visitRepository, EntityMapper entityMapper) {
        this.visitRepository = visitRepository;
        this.entityMapper = entityMapper;
    }

    public VisitDto saveVisit(Visit visit) {
        return entityMapper.mapToVisitDto(visitRepository.save(visit));
    }

    public List<VisitDto> getAllVisits() {
        return visitRepository.findAll().stream().map(entityMapper::mapToVisitDto).toList();
    }

    public Optional<VisitDto> getVisitById(Long id) {
        return visitRepository.findById(id).map(entityMapper::mapToVisitDto);
    }

    public void deleteVisit(Long id) {
        visitRepository.deleteById(id);
    }

    public List<VisitDto> getVisitsFromDatetimeToDatetime(LocalDateTime from, LocalDateTime to) {
        return visitRepository.findAllByDateAfterAndDateBefore(from, to).stream().map(entityMapper::mapToVisitDto).toList();
    }

    public Page<VisitDto> searchVisits(VisitSearchCriteria criteria) {
        Pageable pageable = PageRequest.of(
                criteria.getPage() != null ? criteria.getPage() : 0,
                criteria.getSize() != null ? criteria.getSize() : 10,
                Sort.by(
                        criteria.getSortDirection() != null && criteria.getSortDirection().equalsIgnoreCase("desc") ?
                                Sort.Direction.DESC : Sort.Direction.ASC,
                        criteria.getSortBy() != null ? criteria.getSortBy() : "date"
                )
        );

        if (criteria.getDateFrom() != null && criteria.getDateTo() != null
                && criteria.getEmployeeId() != null && criteria.getServiceId() != null && criteria.getClientId() != null) {
            return visitRepository.findByDateBetweenAndEmployeeIdAndServiceIdAndClientId(
                    criteria.getDateFrom(), criteria.getDateTo(),
                    criteria.getEmployeeId(), criteria.getServiceId(), criteria.getClientId(),
                    pageable
            ).map(entityMapper::mapToVisitDto);
        } else if (criteria.getDateFrom() != null && criteria.getDateTo() != null) {
            return visitRepository.findByDateBetween(criteria.getDateFrom(), criteria.getDateTo(), pageable)
                    .map(entityMapper::mapToVisitDto);
        } else if (criteria.getEmployeeId() != null) {
            return visitRepository.findByEmployeeId(criteria.getEmployeeId(), pageable)
                    .map(entityMapper::mapToVisitDto);
        } else if (criteria.getServiceId() != null) {
            return visitRepository.findByServiceId(criteria.getServiceId(), pageable)
                    .map(entityMapper::mapToVisitDto);
        } else if (criteria.getClientId() != null) {
            return visitRepository.findByClientId(criteria.getClientId(), pageable)
                    .map(entityMapper::mapToVisitDto);
        } else {
            return visitRepository.findAll(pageable).map(entityMapper::mapToVisitDto);
        }
    }

    public List<Object[]> getVisitCountByEmployee() {
        return visitRepository.countVisitsByEmployee();
    }

    public List<Object[]> getAllVisitsWithEmployeeName() {
        return visitRepository.findAllVisitsWithEmployeeName();
    }
}
