package com.mbunda.visitmanagement.controller.web;

import com.mbunda.visitmanagement.service.ClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientWebController {

    private final ClientService clientService;

    public ClientWebController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public String clientsList(Model model) {
        model.addAttribute("allClients", clientService.getAllClients());
        return "clients";
    }

    @GetMapping("/delete/:id")
    public String deleteClient(@RequestParam("id") Long id, Model model) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}
