package com.mbunda.visitmanagement.controller;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.domain.Service;
import com.mbunda.visitmanagement.dto.ServiceDto;
import com.mbunda.visitmanagement.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public ResponseEntity<ServiceDto> createService(@RequestBody Service service) {
        ServiceDto savedService = serviceService.saveService(service);
        return new ResponseEntity<>(savedService, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        List<ServiceDto> services = serviceService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/addExistingEmployee/{employeeId}")
    public ResponseEntity<ServiceDto> addExistingEmployeeToService(@PathVariable Long id, @PathVariable Long employeeId) {
        ServiceDto service = serviceService.addEmployeeToService(id, employeeId);
        return ResponseEntity.ok(service);
    }

    @PatchMapping("/{id}/removeEmployee/{employeeId}")
    public ResponseEntity<ServiceDto> removeEmployeeFromService(@PathVariable Long id, @PathVariable Long employeeId) {
        ServiceDto service = serviceService.removeEmployeeFromService(id, employeeId);
        return ResponseEntity.ok(service);
    }

    @PostMapping("/{id}/addNewEmployee")
    public ResponseEntity<ServiceDto> addNewEmployeeToService(@PathVariable Long id, @RequestBody Employee employee) {
        ServiceDto service = serviceService.addNewEmployeeToService(id, employee);
        return ResponseEntity.ok(service);
    }
}
