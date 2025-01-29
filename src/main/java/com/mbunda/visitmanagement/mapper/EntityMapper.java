package com.mbunda.visitmanagement.mapper;

import com.mbunda.visitmanagement.domain.Client;
import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.domain.Service;
import com.mbunda.visitmanagement.domain.Visit;
import com.mbunda.visitmanagement.dto.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class EntityMapper {
    EmployeeInfo mapToEmployeeInfo(Employee employee) {
        return new EmployeeInfo(employee.getId(), employee.getName(), employee.getSpecialization());
    }

    public EmployeeDto mapToEmployeeDto(Employee employee) {
        List<VisitInfo> visits = Optional.ofNullable(employee.getVisits())
                .orElseGet(Collections::emptyList) // Use an empty list if visits is null
                .stream()
                .map(this::mapToVisitInfo)
                .toList();
        List<ServiceInfo> services = Optional.ofNullable(employee.getServices())
                .orElseGet(Collections::emptyList) // Use an empty list if services is null
                .stream()
                .map(this::mapToServiceInfo)
                .toList();
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getSpecialization(), visits, services);
    }

    ServiceInfo mapToServiceInfo(Service service) {
        return new ServiceInfo(service.getId(), service.getName(), service.getPrice());
    }

    public ServiceDto mapToServiceDto(Service service) {
        List<EmployeeInfo> employees = Optional.ofNullable(service.getEmployees())
                .orElseGet(Collections::emptyList) // Use an empty list if employees is null
                .stream()
                .map(this::mapToEmployeeInfo)
                .toList();
        return new ServiceDto(service.getId(), service.getName(), service.getPrice(), employees);
    }

    ClientInfo mapToClientInfo(Client client) {
        return new ClientInfo(client.getId(), client.getName(), client.getEmail(), client.getPhoneNumber());
    }

    public ClientDto mapToClientDto(Client client) {
        List<VisitInfo> visits = Optional.ofNullable(client.getVisits())
                .orElseGet(Collections::emptyList) // Use an empty list if visits is null
                .stream()
                .map(this::mapToVisitInfo)
                .toList();
        return new ClientDto(client.getId(), client.getName(), client.getEmail(), client.getPhoneNumber(), visits);
    }

    VisitInfo mapToVisitInfo(Visit visit) {
        return new VisitInfo(visit.getId(), visit.getDate());
    }

    public VisitDto mapToVisitDto(Visit visit) {
        EmployeeInfo employee = Optional.ofNullable(visit.getEmployee())
                .map(this::mapToEmployeeInfo)
                .orElse(null); // Use null if employee is null
        ServiceInfo service = Optional.ofNullable(visit.getService())
                .map(this::mapToServiceInfo)
                .orElse(null); // Use null if service is null
        ClientInfo client = Optional.ofNullable(visit.getClient())
                .map(this::mapToClientInfo)
                .orElse(null); // Use null if client is null
        return new VisitDto(visit.getId(), visit.getDate(), employee, service, client);
    }
}
