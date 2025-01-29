package com.mbunda.visitmanagement.controller.web;

import com.mbunda.visitmanagement.service.ClientService;
import com.mbunda.visitmanagement.service.EmployeeService;
import com.mbunda.visitmanagement.service.ServiceService;
import com.mbunda.visitmanagement.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class WebController {

    private final VisitService visitService;
    private final EmployeeService employeeService;
    private final ClientService clientService;
    private final ServiceService serviceService;

    public WebController(VisitService visitService, EmployeeService employeeService, ClientService clientService, ServiceService serviceService) {
        this.visitService = visitService;
        this.employeeService = employeeService;
        this.clientService = clientService;
        this.serviceService = serviceService;
    }

    @GetMapping({"", "/"})
    public String all(Model model) {
        model.addAttribute("allVisits", visitService.getAllVisits());
        model.addAttribute("allEmployees", employeeService.getAllEmployees());
        model.addAttribute("allClients", clientService.getAllClients());
        model.addAttribute("allServices", serviceService.getAllServices());
        return "all";
    }
}