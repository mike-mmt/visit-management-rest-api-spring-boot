package com.mbunda.visitmanagement.service;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.domain.Service;
import com.mbunda.visitmanagement.dto.*;
import com.mbunda.visitmanagement.mapper.EntityMapper;
import com.mbunda.visitmanagement.repository.EmployeeRepository;
import com.mbunda.visitmanagement.repository.ServiceRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;
    private final EntityMapper entityMapper;

    public ServiceService(ServiceRepository serviceRepository, EmployeeRepository employeeRepository, EntityMapper entityMapper) {
        this.serviceRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
        this.entityMapper = entityMapper;
    }

    public ServiceDto saveService(Service service) {
        return entityMapper.mapToServiceDto(serviceRepository.save(service));
    }

    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll().stream().map(entityMapper::mapToServiceDto).toList();
    }

    public Optional<ServiceDto> getServiceById(Long id) {
        return serviceRepository.findById(id).map(entityMapper::mapToServiceDto);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    public ServiceDto addEmployeeToService(Long id, Long employeeId) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Service not found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        // Avoid duplicate additions
        if (!service.getEmployees().contains(employee)) {
            service.getEmployees().add(employee);
        }
        if (!employee.getServices().contains(service)) {
            employee.getServices().add(service);
        }

        // Save both sides of the relationship
        employeeRepository.save(employee);
        return  entityMapper.mapToServiceDto(serviceRepository.save(service));
    }

    public ServiceDto removeEmployeeFromService(Long id, Long employeeId) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Service not found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        service.getEmployees().remove(employee);
        return entityMapper.mapToServiceDto(serviceRepository.save(service));
    }

    public ServiceDto addNewEmployeeToService(Long id, Employee employee) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Service not found"));
        System.out.println("Employee: " + employee);
        service.getEmployees().add(employee);
        System.out.println("Service: " + service);
        return entityMapper.mapToServiceDto(serviceRepository.save(service));
    }


}
