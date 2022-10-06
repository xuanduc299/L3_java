package com.globits.da.repository;

import com.globits.da.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee,UUID> {
    Employee getEmployeeById(UUID id);

    boolean existsByCode(String code);

}
