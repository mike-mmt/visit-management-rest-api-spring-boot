package com.mbunda.visitmanagement.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Service}
 */
public record ServiceDto(Long id, String name, Double price, List<EmployeeInfo> employees) implements Serializable {
}