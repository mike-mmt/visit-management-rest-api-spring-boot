package com.mbunda.visitmanagement.service;

import com.mbunda.visitmanagement.domain.Visit;
import com.mbunda.visitmanagement.dto.VisitDto;
import com.mbunda.visitmanagement.mapper.EntityMapper;
import com.mbunda.visitmanagement.repository.VisitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
}
