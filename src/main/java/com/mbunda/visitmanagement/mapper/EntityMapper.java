package com.mbunda.visitmanagement.mapper;

import com.mbunda.visitmanagement.domain.Client;
import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.domain.Service;
import com.mbunda.visitmanagement.domain.Visit;
import com.mbunda.visitmanagement.dto.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntityMapper {
    EmployeeInfo mapToEmployeeInfo(Employee employee) {
        return new EmployeeInfo(employee.getId(), employee.getName(), employee.getSpecialization());
    }

    public EmployeeDto mapToEmployeeDto(Employee employee) {
        List<VisitInfo> visits = employee.getVisits().stream()
                .map(this::mapToVisitInfo)
                .toList();
        List<ServiceInfo> services = employee.getServices().stream()
                .map(this::mapToServiceInfo)
                .toList();
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getSpecialization(), visits, services);
    }

    ServiceInfo mapToServiceInfo(Service service) {
        return new ServiceInfo(service.getId(), service.getName(), service.getPrice());
    }

    public ServiceDto mapToServiceDto(Service service) {
        List<EmployeeInfo> employees = service.getEmployees().stream()
                .map(this::mapToEmployeeInfo)
                .toList();
        return new ServiceDto(service.getId(), service.getName(), service.getPrice(), employees);
    }

    ClientInfo mapToClientInfo(Client client) {
        return new ClientInfo(client.getId(), client.getName(), client.getEmail(), client.getPhoneNumber());
    }

    public ClientDto mapToClientDto(Client client) {
        List<VisitInfo> visits = client.getVisits().stream().map(this::mapToVisitInfo).toList();
        return new ClientDto(client.getId(), client.getName(), client.getEmail(), client.getPhoneNumber(), visits);
    }

    VisitInfo mapToVisitInfo(Visit visit) {
        return new VisitInfo(visit.getId(), visit.getDate());
    }

    public VisitDto mapToVisitDto(Visit visit) {
        EmployeeInfo employee = this.mapToEmployeeInfo(visit.getEmployee());
        ServiceInfo service = this.mapToServiceInfo(visit.getService());
        ClientInfo client = this.mapToClientInfo(visit.getClient());
        return new VisitDto(visit.getId(), visit.getDate(), employee, service, client);
    }
}
