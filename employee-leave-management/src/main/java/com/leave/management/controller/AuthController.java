package com.leave.management.controller;

import com.leave.management.entity.Employee;
import com.leave.management.entity.Role;
import com.leave.management.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, 
                        @RequestParam String password, 
                        HttpSession session, 
                        Model model) {
        Employee employee = employeeService.authenticate(email, password);
        if (employee != null) {
            session.setAttribute("loggedInUser", employee);
            if (employee.getRole() == Role.MANAGER) {
                return "redirect:/manager/dashboard";
            } else {
                return "redirect:/employee/dashboard";
            }
        }
        model.addAttribute("error", "Invalid email or password!");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
