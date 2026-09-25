package com.leave.management.repository;

import com.leave.management.entity.LeaveRequest;
import com.leave.management.entity.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByEmployeeIdOrderByAppliedDateDesc(Long employeeId);
    List<LeaveRequest> findByStatusOrderByAppliedDateDesc(LeaveStatus status);
}
