package com.mbunda.visitmanagement.controller.web;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeService employeeService;

    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping({"", "/"})
    public String employeesList(Model model) {
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        return "employees";
    }

    @GetMapping("/add")
    public String addEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee(0L,"","",null,null));
        return "employee-form";
    }

    @PostMapping("/add")
    public String addEmployeeSubmit(@ModelAttribute Employee employee, BindingResult result, Model model) {
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }
}
