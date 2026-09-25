# Employee Leave Management System

A full-stack web application built with Spring Boot, MySQL, and Thymeleaf for managing employee leave requests, approvals, and history tracking.

## Tech Stack
* **Backend:** Java 17, Spring Boot, Spring Data JPA
* **Frontend:** HTML5, CSS3, JavaScript, Thymeleaf
* **Database:** MySQL
* **Build Tool:** Maven

## Features
* **Employee Login & Role-Based Workflows:** Separate dashboards and permissions for Employees and Managers.
* **Apply Leave:** Employees can submit leave requests specifying Leave Type, Start/End Dates, and Reason.
* **Manager Approval/Rejection:** Managers can review pending requests and approve or reject them with optional comments.
* **Leave History & Tracking:** View comprehensive history of all requested leaves and current status (`PENDING`, `APPROVED`, `REJECTED`).

## Setup & Execution

1. **Database Setup:**
   * Create a MySQL database:
     ```sql
     CREATE DATABASE leave_db;
     ```

2. **Configure Database Credentials:**
   * Update `src/main/resources/application.properties`:
     ```properties
     spring.datasource.username=YOUR_MYSQL_USERNAME
     spring.datasource.password=YOUR_MYSQL_PASSWORD
     ```

3. **Run Application:**
   * Using Maven terminal:
     ```bash
     mvn spring-boot:run
     ```
   * Access application in web browser at: `http://localhost:8080/login`
