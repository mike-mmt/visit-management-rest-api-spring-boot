package com.mbunda.visitmanagement.service;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.domain.Service;
import com.mbunda.visitmanagement.repository.EmployeeRepository;
import com.mbunda.visitmanagement.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;

    public ServiceService(ServiceRepository serviceRepository, EmployeeRepository employeeRepository) {
        this.serviceRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
    }

    public Service saveService(Service service) {
        return serviceRepository.save(service);
    }

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    // TODO: @Transactional + DTO 
    public Service addEmployeeToService(Long id, Long employeeId) {
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
        return serviceRepository.save(service);
    }

    public Service removeEmployeeFromService(Long id, Long employeeId) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Service not found"));
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new IllegalArgumentException("Employee not found"));
        service.getEmployees().remove(employee);
        return serviceRepository.save(service);
    }

    public Service addNewEmployeeToService(Long id, Employee employee) {
        Service service = serviceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Service not found"));
        System.out.println("Employee: " + employee);
        service.getEmployees().add(employee);
        System.out.println("Service: " + service);
        return serviceRepository.save(service);
    }
}
