package com.employee.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.Employee;
import com.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService empService;
	
	@GetMapping("/getAllEmployees")
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmpId(@PathVariable(value="id") Long empId){
		Optional<Employee> employee = empService.getEmpById(empId);
		if(employee.isPresent())
			return ResponseEntity.ok().body(employee.get());
		else
			return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/createEmployee")
	public void createEmployee(@Valid @RequestBody Employee employee) {
		//return empService.createEmployee(employee);
		empService.createEmployee(employee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long empId, 
			@Valid @RequestBody Employee empDetails){
		Optional<Employee> employee = empService.updateEmployees(empId, empDetails);
		if(employee.isPresent())
			return ResponseEntity.ok().body(employee.get());
		else
			return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public String deleteEmployee(@PathVariable(value="id") Long empId){
		boolean deleteEmployee = empService.deleteEmployee(empId);
		if(deleteEmployee)
			return "Employee deleted successfully ";
		else
			return "Employee not found";
	}
	
	@GetMapping("/getAllEmployeesByDOJ")
	public List<Employee> getAllEmployeesByDOJ(){
		return empService.getAllEmployeesByDOJ();
	}
	
}
