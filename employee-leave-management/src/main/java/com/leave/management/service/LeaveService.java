package com.leave.management.service;

import com.leave.management.entity.LeaveRequest;
import com.leave.management.entity.LeaveStatus;
import com.leave.management.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    private LeaveRequestRepository leaveRequestRepository;

    public LeaveRequest applyForLeave(LeaveRequest leaveRequest) {
        leaveRequest.setStatus(LeaveStatus.PENDING);
        return leaveRequestRepository.save(leaveRequest);
    }

    public List<LeaveRequest> getLeaveHistoryByEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployeeIdOrderByAppliedDateDesc(employeeId);
    }

    public List<LeaveRequest> getAllLeaveRequests() {
        return leaveRequestRepository.findAll();
    }

    public List<LeaveRequest> getPendingLeaveRequests() {
        return leaveRequestRepository.findByStatusOrderByAppliedDateDesc(LeaveStatus.PENDING);
    }

    public LeaveRequest updateLeaveStatus(Long requestId, LeaveStatus status, String comments) {
        LeaveRequest request = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Leave request not found with ID: " + requestId));
        request.setStatus(status);
        request.setManagerComments(comments);
        return leaveRequestRepository.save(request);
    }
}
