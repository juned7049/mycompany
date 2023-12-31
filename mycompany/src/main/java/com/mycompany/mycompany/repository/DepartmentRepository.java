package com.mycompany.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mycompany.mycompany.entites.Department;

public interface DepartmentRepository  extends JpaRepository<Department, Long>, JpaSpecificationExecutor<Department>{

}
