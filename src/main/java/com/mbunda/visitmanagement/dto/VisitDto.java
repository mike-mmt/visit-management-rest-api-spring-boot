package com.mbunda.visitmanagement.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Visit}
 */
public record VisitDto(Long id, LocalDateTime date, EmployeeInfo employee, ServiceInfo service,
                       ClientInfo client) implements Serializable {
}