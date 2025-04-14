package com.employee.service;

import java.util.List;
import java.util.Optional;

import com.employee.dto.Employee;

public interface EmployeeService {
	
	List<Employee> getAllEmployees();
	Optional<Employee> getEmpById(Long empId);
	void createEmployee(Employee emp);
	Optional<Employee> updateEmployees(Long empId,Employee empDetails);
	boolean deleteEmployee(Long empId);
	List<Employee> getAllEmployeesByDOJ();

}
