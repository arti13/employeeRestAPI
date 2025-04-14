package com.employee.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.employee.dto.Employee;
import com.employee.projection.EmployeeProjection;

import jakarta.transaction.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query(value = "SELECT * FROM employees ", nativeQuery = true)
	List<Employee> findAllEmployees();
	
	/*@Query("SELECT new com.example.dto.EmployeeDto(e.empId, e.name, e.department) FROM Employee e WHERE e.empId = :empId")
	Optional<Employee> getEmpById(@Param("empId") Long empId);*/
	
	@Query(value = "SELECT emp_id AS empId, emp_name, email, department FROM employees WHERE emp_id = :empId", nativeQuery = true)
    Optional<EmployeeProjection> getEmpById(@Param("empId") Long empId);
	
	@Query(value = "SELECT * FROM employees WHERE doj >= CURRENT_DATE - 30", nativeQuery = true)
    List<Employee> getEmpByDOJ();
	
	//@Modifying
	//@Transactional
	@Query(value ="INSERT INTO employees (emp_name, email, department)  VALUES(:empName,:email,:department)",nativeQuery= true)
	Employee insertEmployee(@Param("empName") String empName, @Param("email") String email,@Param("department")String department);
}
