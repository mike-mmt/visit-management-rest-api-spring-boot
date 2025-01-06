package com.mbunda.visitmanagement.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Employee}
 */
public record EmployeeDto(Long id, String name, String specialization, List<VisitInfo> visits,
                          List<ServiceInfo> services) implements Serializable {
}