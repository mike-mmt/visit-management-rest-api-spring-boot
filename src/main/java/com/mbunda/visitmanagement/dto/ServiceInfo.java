package com.mbunda.visitmanagement.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Service}
 */
public record ServiceInfo(Long id, String name, Double price) implements Serializable {
}