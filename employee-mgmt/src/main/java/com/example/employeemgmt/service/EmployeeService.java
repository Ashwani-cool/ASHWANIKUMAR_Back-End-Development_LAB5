package com.example.employeemgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeemgmt.dao.EmployeeRepository;
import com.example.employeemgmt.entity.Employee;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeService(EmployeeRepository empRepository) {
		employeeRepository = empRepository;
	}
	
	public List<Employee> findAll(){
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}
	
	
	public Employee findById(int theId) {
		Optional<Employee> result = employeeRepository.findById(theId);

		Employee employee = null;

		if (result.isPresent()) {
			employee = result.get();
		} else {
			// we didn't find the book
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return employee;
	}

	public void save(Employee employee) {
		employeeRepository.save(employee);
	}

	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}
}
