package com.mycompany.mycompany.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.mycompany.mycompany.Dto.SearchRequestDto;

import com.mycompany.mycompany.entites.Department;
import com.mycompany.mycompany.entites.Employee;
import com.mycompany.mycompany.entites.Office;
import com.mycompany.mycompany.repository.DepartmentRepository;
import com.mycompany.mycompany.repository.EmployeeRepository;
import com.mycompany.mycompany.repository.OfficeRepository;

import jakarta.persistence.criteria.Predicate;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private OfficeRepository officeRepository;

    @GetMapping("/all")
    public ResponseEntity<SearchRequestDto> searchAll(
            @RequestParam(name = "searchterm",required = false) String keyword) {
        List<Employee> employees = employeeRepository.findAll(getEmployeeSpecification(keyword));
        List<Department> departments = departmentRepository.findAll(getDepartmentSpecification(keyword));
        List<Office> offices = officeRepository.findAll(getOfficeSpecification(keyword));

      SearchRequestDto resultDTO = new SearchRequestDto(employees, departments, offices);
      System.out.println(resultDTO);
      return ResponseEntity.ok(resultDTO);
    }

    private Specification<Employee> getEmployeeSpecification(String keyword) {
    	System.out.println(keyword);
    	return (root, query, criteriaBuilder) -> {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
            Predicate departmentPredicate = criteriaBuilder.like(root.get("department").get("name"), "%" + keyword + "%");
            Predicate officePredicate = criteriaBuilder.like(root.get("department").get("office").get("country"), "%" + keyword + "%");
            
            return criteriaBuilder.or(namePredicate, departmentPredicate, officePredicate);
        };
    }

    private Specification<Department> getDepartmentSpecification(String keyword) {
        return (root, query, criteriaBuilder) -> {
            Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
            Predicate officePredicate = criteriaBuilder.like(root.get("office").get("country"), "%" + keyword + "%");
            
            return criteriaBuilder.or(namePredicate, officePredicate);
        };
    }

    private Specification<Office> getOfficeSpecification(String keyword) {
        return (root, query, criteriaBuilder) -> {
            Predicate addressPredicate = criteriaBuilder.like(root.get("address"), "%" + keyword + "%");
            Predicate countryPredicate = criteriaBuilder.like(root.get("country"), "%" + keyword + "%");
            
            return criteriaBuilder.or(addressPredicate, countryPredicate);
        };
    }
}

