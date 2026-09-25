package com.leave.management.controller;

import com.leave.management.entity.Employee;
import com.leave.management.entity.LeaveRequest;
import com.leave.management.entity.LeaveType;
import com.leave.management.service.LeaveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("loggedInUser");
        if (employee == null) return "redirect:/login";

        model.addAttribute("employee", employee);
        model.addAttribute("leaveHistory", leaveService.getLeaveHistoryByEmployee(employee.getId()));
        return "employee-dashboard";
    }

    @GetMapping("/apply-leave")
    public String showApplyLeaveForm(HttpSession session, Model model) {
        Employee employee = (Employee) session.getAttribute("loggedInUser");
        if (employee == null) return "redirect:/login";

        model.addAttribute("leaveRequest", new LeaveRequest());
        model.addAttribute("leaveTypes", LeaveType.values());
        return "apply-leave";
    }

    @PostMapping("/apply-leave")
    public String applyLeave(@ModelAttribute("leaveRequest") LeaveRequest leaveRequest, HttpSession session) {
        Employee employee = (Employee) session.getAttribute("loggedInUser");
        if (employee == null) return "redirect:/login";

        leaveRequest.setEmployee(employee);
        leaveService.applyForLeave(leaveRequest);
        return "redirect:/employee/dashboard";
    }
}
