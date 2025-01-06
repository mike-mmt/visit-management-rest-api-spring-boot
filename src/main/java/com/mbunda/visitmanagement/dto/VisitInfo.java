package com.mbunda.visitmanagement.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Visit}
 */
public record VisitInfo(Long id, LocalDateTime date) implements Serializable {
}