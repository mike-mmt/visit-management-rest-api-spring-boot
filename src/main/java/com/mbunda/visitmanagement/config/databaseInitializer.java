package com.mbunda.visitmanagement.config;

import com.mbunda.visitmanagement.domain.Client;
import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.domain.Service;
import com.mbunda.visitmanagement.domain.Visit;
import com.mbunda.visitmanagement.service.ClientService;
import com.mbunda.visitmanagement.service.EmployeeService;
import com.mbunda.visitmanagement.service.ServiceService;
import com.mbunda.visitmanagement.service.VisitService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class databaseInitializer implements CommandLineRunner {

    private final ServiceService serviceService;
    private final EmployeeService employeeService;
    private final ClientService clientService;
    private final VisitService visitService;

    public databaseInitializer(ServiceService serviceService, EmployeeService employeeService, ClientService clientService, VisitService visitService) {
        this.serviceService = serviceService;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create Services
        Service haircut = new Service(null, "Cięcie włosów długich", 120.0, null);
        Service manicure = new Service(null, "Manicure", 80.0, null);
        Service pedicure = new Service(null, "Pedicure", 100.0, null);
        Service massage = new Service(null, "Masaż relaksacyjny", 150.0, null);
        Service facial = new Service(null, "Zabieg na twarz", 200.0, null);

        serviceService.saveService(haircut);
        serviceService.saveService(manicure);
        serviceService.saveService(pedicure);
        serviceService.saveService(massage);
        serviceService.saveService(facial);

        // Create Employees
        Employee alice = new Employee(null, "Alice", "Hairdresser", null, null);
        Employee bob = new Employee(null, "Bob", "Manicurist", null, null);
        Employee charlie = new Employee(null, "Charlie", "Masseur", null, null);
        Employee diana = new Employee(null, "Diana", "Esthetician", null, null);
        Employee eve = new Employee(null, "Eve", "Pedicurist", null, null);

        // Assign Services to Employees
        alice.setServices(Arrays.asList(haircut));
        bob.setServices(Arrays.asList(massage));
        charlie.setServices(Arrays.asList(massage, haircut));
        diana.setServices(Arrays.asList(facial, manicure, pedicure));
        eve.setServices(Arrays.asList(haircut, massage, manicure, pedicure, facial));

        employeeService.saveEmployee(alice);
        employeeService.saveEmployee(bob);
        employeeService.saveEmployee(charlie);
        employeeService.saveEmployee(diana);
        employeeService.saveEmployee(eve);

        // Create Clients
        Client john = new Client(null, "John Doe", "john.doe@example.com", "123-456-7890", null);
        Client jane = new Client(null, "Jane Smith", "jane.smith@example.com", "987-654-3210", null);
        Client mike = new Client(null, "Mike Johnson", "mike.johnson@example.com", "555-555-5555", null);
        Client sarah = new Client(null, "Sarah Brown", "sarah.brown@example.com", "111-222-3333", null);
        Client emily = new Client(null, "Emily Davis", "emily.davis@example.com", "444-444-4444", null);

        clientService.saveClient(jane);
        clientService.saveClient(mike);
        clientService.saveClient(sarah);
        clientService.saveClient(emily);
        clientService.saveClient(john);

        // Create Visits
        Visit visit1 = new Visit(null, LocalDateTime.of(2023, 10, 15, 10, 0), alice, haircut, john);
        Visit visit2 = new Visit(null, LocalDateTime.of(2023, 10, 16, 14, 0), bob, manicure, jane);
        Visit visit3 = new Visit(null, LocalDateTime.of(2023, 10, 17, 11, 0), charlie, massage, mike);
        Visit visit4 = new Visit(null, LocalDateTime.of(2023, 10, 18, 16, 0), diana, facial, sarah);
        Visit visit5 = new Visit(null, LocalDateTime.of(2023, 10, 19, 13, 0), eve, pedicure, emily);

        visitService.saveVisit(visit1);
        visitService.saveVisit(visit2);
        visitService.saveVisit(visit3);
        visitService.saveVisit(visit4);
        visitService.saveVisit(visit5);
    }
}
