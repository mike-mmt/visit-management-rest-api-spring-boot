package com.mbunda.visitmanagement.controller.web;

import com.mbunda.visitmanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeWebController {

    private final EmployeeService employeeService;

    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String employeesList(Model model) {
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        return "employees";
    }
}
