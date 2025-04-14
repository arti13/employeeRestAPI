package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.dto.Employee;
import com.employee.logging.LogExecutionTime;
import com.employee.projection.EmployeeProjection;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository empRepository;
	
	//@Autowired 
	EmployeeProjection projection;
	
	
	
	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return empRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmpById(Long empId) {
		
		return Optional.of(empRepository.getEmpById(empId)
				.map(projection -> new Employee(
						projection.getEmpId(),
						projection.getEmpName(),
						projection.getEmail(),
						projection.getDepartment())						
					))
				.orElse(null);
	}

	@Override
	@LogExecutionTime
	public void createEmployee(Employee emp) {
		// TODO Auto-generated method stub		
		Employee saveEmployee = empRepository.save(emp);
		System.out.println("Employee inserted as EmpID: "+saveEmployee.getEmpId()+ " Emp Name : "+saveEmployee.getEmpName());
		empRepository.save(emp);
		//return empRepository.insertEmployee(emp.getEmpName(),emp.getEmail(),emp.getDepartment());
		//return saveEmployee;
	}

	@Override
	public Optional<Employee> updateEmployees(Long empId, Employee empDetails) {
		Optional<Employee> optionalEmployee= empRepository.findById(empId);
		if(optionalEmployee.isPresent()) {
			Employee employee = optionalEmployee.get();
			employee.setEmpName(empDetails.getEmpName());
			employee.setDepartment(empDetails.getDepartment());
			employee.setEmail(empDetails.getEmail());
			return Optional.of(empRepository.save(employee));
		}
		else
			return Optional.empty();
	}

	@Override
	public boolean deleteEmployee(Long empId) {
		Optional<Employee> employee = Optional.ofNullable(empRepository.getById(empId));
		if(employee.isPresent()) {
			empRepository.delete(employee.get());
			return true;
		}
		else
			return false;
	}

	@Override
	public List<Employee> getAllEmployeesByDOJ() {
		List<Employee> dojList = empRepository.getEmpByDOJ();
		for(Employee emp:dojList)
			System.out.println("List of Employees as per DOJ EmpId : " +emp.getEmpId() + " Emp Name: " +emp.getEmpName());
		return empRepository.getEmpByDOJ();
	}

}
