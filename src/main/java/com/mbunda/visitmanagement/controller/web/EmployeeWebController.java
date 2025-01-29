package com.mbunda.visitmanagement.controller.web;

import com.mbunda.visitmanagement.domain.Employee;
import com.mbunda.visitmanagement.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public String employee(Model model, @PathVariable Long id) {
        model.addAttribute("employee", employeeService.getEmployeeById(id).orElse(null));
        return "employee";
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

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}
