package com.mbunda.visitmanagement.dto;

import java.io.Serializable;

/**
 * DTO for {@link com.mbunda.visitmanagement.domain.Client}
 */
public record ClientInfo(Long id, String name, String email, String phoneNumber) implements Serializable {
}