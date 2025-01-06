package com.mbunda.visitmanagement.service;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.dto.EmployeeDto;
import com.mbunda.visitmanagement.mapper.EntityMapper;
import com.mbunda.visitmanagement.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EntityMapper entityMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EntityMapper entityMapper) {
        this.employeeRepository = employeeRepository;
        this.entityMapper = entityMapper;
    }

    public EmployeeDto saveEmployee(Employee employee) {
        return entityMapper.mapToEmployeeDto(employeeRepository.save(employee));
    }

    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream().map(entityMapper::mapToEmployeeDto).toList();
    }

    public Optional<EmployeeDto> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(entityMapper::mapToEmployeeDto);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
