package com.example.employeemgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employeemgmt.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
