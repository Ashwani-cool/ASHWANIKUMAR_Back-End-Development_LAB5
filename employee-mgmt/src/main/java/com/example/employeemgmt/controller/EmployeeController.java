package com.example.employeemgmt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.employeemgmt.entity.Employee;
import com.example.employeemgmt.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	@GetMapping("/list")
	public String listEmployees(Model theModel) {
			
			// get Employees from database
			List<Employee> employee = employeeService.findAll();
			
			// add to the spring model
			theModel.addAttribute("employees", employee);
			
			return "employees/list-employees";
		}
	
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		
		// create model attribute to bind form data
		Employee employee = new Employee();
		
		theModel.addAttribute("employee", employee);
		
		return "employees/employee-form";
	}

	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,
									Model theModel) {
		
		// get the Employee from the service
		Employee employee = employeeService.findById(theId);
		
		// set Employee as a model attribute to pre-populate the form
		theModel.addAttribute("employee", employee);
		
		// send over to our form
		return "employees/employee-form";			
	}
	
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		
		// save the Employee
		employeeService.save(employee);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/list";
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		// delete the Employee
		employeeService.deleteById(theId);
		
		// redirect to /Employees/list
		return "redirect:/employees/list";
		
	}
}
