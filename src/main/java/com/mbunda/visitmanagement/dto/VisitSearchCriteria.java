package com.mbunda.visitmanagement.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitSearchCriteria {
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private Long employeeId;
    private Long serviceId;
    private Long clientId;
    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortDirection;
}