package com.mbunda.visitmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeVisitCountDto {
    private String employeeName;
    private Long visitCount;
}