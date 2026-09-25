package com.leave.management.controller;

import com.leave.management.entity.Employee;
import com.leave.management.entity.LeaveStatus;
import com.leave.management.entity.Role;
import com.leave.management.service.LeaveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private LeaveService leaveService;

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        Employee manager = (Employee) session.getAttribute("loggedInUser");
        if (manager == null || manager.getRole() != Role.MANAGER) {
            return "redirect:/login";
        }

        model.addAttribute("manager", manager);
        model.addAttribute("pendingRequests", leaveService.getPendingLeaveRequests());
        model.addAttribute("allRequests", leaveService.getAllLeaveRequests());
        return "manager-dashboard";
    }

    @PostMapping("/update-status")
    public String updateLeaveStatus(@RequestParam Long requestId, 
                                    @RequestParam LeaveStatus status, 
                                    @RequestParam(required = false) String comments) {
        leaveService.updateLeaveStatus(requestId, status, comments);
        return "redirect:/manager/dashboard";
    }
}
