package com.mmit.model.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mmit.model.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
