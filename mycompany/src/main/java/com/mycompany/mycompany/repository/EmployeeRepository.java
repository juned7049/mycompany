package com.mycompany.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mycompany.mycompany.entites.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> ,JpaSpecificationExecutor<Employee>{

	
	
	
}
