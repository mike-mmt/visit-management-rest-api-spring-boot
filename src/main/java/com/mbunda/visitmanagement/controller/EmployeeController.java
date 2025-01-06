package com.mbunda.visitmanagement.controller;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
        private final EmployeeService employeeService;

        public EmployeeController(EmployeeService employeeService) {
            this.employeeService = employeeService;
        }

        @PostMapping
        public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
            Employee savedEmployee = employeeService.saveEmployee(employee);
            return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<List<Employee>> getAllEmployees() {
            List<Employee> employees = employeeService.getAllEmployees();
            return ResponseEntity.ok(employees);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
            return employeeService.getEmployeeById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
            employeeService.deleteEmployee(id);
            return ResponseEntity.noContent().build();
        }
}
