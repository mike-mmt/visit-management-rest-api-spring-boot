package com.mbunda.visitmanagement.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Employee}
 */
public record EmployeeInfo(Long id, String name, String specialization) implements Serializable {
}