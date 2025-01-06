package com.mbunda.visitmanagement.config;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.domain.Service;
import com.mbunda.visitmanagement.repository.EmployeeRepository;
import com.mbunda.visitmanagement.repository.ServiceRepository;
import com.mbunda.visitmanagement.repository.VisitRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class databaseInitializer implements CommandLineRunner {

    private final ServiceRepository serviceRepository;
    private final EmployeeRepository employeeRepository;

    public databaseInitializer(ServiceRepository serviceRepository, EmployeeRepository employeeRepository) {
        this.serviceRepository = serviceRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        serviceRepository.save(new Service(null, "Cięcie włosów długich", 120.0, null));
        employeeRepository.save(new Employee(null, "Alice", "Hairdresser", null, null));
    }
}
