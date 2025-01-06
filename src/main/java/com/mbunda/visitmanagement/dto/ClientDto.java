package com.mbunda.visitmanagement.dto;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Client}
 */
public record ClientDto(Long id, String name, String email, String phoneNumber,
                        List<VisitInfo> visits) implements Serializable {
}