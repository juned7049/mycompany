package com.mycompany.mycompany.Controller;

import java.util.ArrayList;
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
import com.mycompany.mycompany.repository.EmployeeRepository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private EmployeeRepository employeeRepository;

    
    
    
    
    
    
    @GetMapping("/all")
    public ResponseEntity<SearchRequestDto> searchAll(
            @RequestParam(name = "employeename", required = false) String employeeName,
            @RequestParam(name = "departmentname", required = false) String departmentName,
            @RequestParam(name = "officecountry", required = false) String officeCountry) {
        List<Employee> employees = employeeRepository.findAll(getEmployeeSpecification(employeeName, departmentName, officeCountry));

        SearchRequestDto resultDTO = new SearchRequestDto(employees);
        return ResponseEntity.ok(resultDTO);
    }

    private Specification<Employee> getEmployeeSpecification(String employeeName, String departmentName, String officeCountry) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            
            if (employeeName != null && !employeeName.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + employeeName + "%"));
            }
            
            if (departmentName != null && !departmentName.isEmpty()) {
                // Join Employee to Department
                Join<Employee, Department> departmentJoin = root.join("department", JoinType.INNER);
                predicates.add(criteriaBuilder.like(departmentJoin.get("name"), "%" + departmentName + "%"));
            }
            
            if (officeCountry != null && !officeCountry.isEmpty()) {
                // Join Employee to Department and Department to Office
                Join<Employee, Department> departmentJoin = root.join("department", JoinType.INNER); 
                Join<Department, Office> officeJoin = departmentJoin.join("office", JoinType.INNER); 
                predicates.add(criteriaBuilder.like(officeJoin.get("country"), "%" + officeCountry + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

//    @GetMapping("/all")
//    public ResponseEntity<SearchRequestDto> searchAll(
//            @RequestParam(name = "searchterm",required = false) List<String> keyword) {
//        List<Employee> employees = employeeRepository.findAll(getEmployeeSpecification(keyword));
//    //    List<Department> departments = departmentRepository.findAll(getDepartmentSpecification(keyword));
//      //  List<Office> offices = officeRepository.findAll(getOfficeSpecification(keyword));
//
//      SearchRequestDto resultDTO = new SearchRequestDto(employees);
//      System.out.println(resultDTO);
//      return ResponseEntity.ok(resultDTO);
//    }
//
//    private Specification<Employee> getEmployeeSpecification(List<String> keywords) {
//    	System.out.println(keywords);
//    	return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            
//            if (keywords != null && !keywords.isEmpty()) {
//                for (String keyword : keywords) {
//                    Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
//                    Predicate departmentPredicate = criteriaBuilder.like(root.get("department").get("name"), "%" + keyword + "%");
//                    Predicate officePredicate = criteriaBuilder.like(root.get("department").get("office").get("country"), "%" + keyword + "%");
//                    predicates.add(criteriaBuilder.or(namePredicate, departmentPredicate, officePredicate));
//                }
//            }
//
//            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    private Specification<Department> getDepartmentSpecification(List<String> keywords) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            
//            if (keywords != null && !keywords.isEmpty()) {
//                for (String keyword : keywords) {
//                    Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + keyword + "%");
//                    Predicate officePredicate = criteriaBuilder.like(root.get("office").get("country"), "%" + keyword + "%");
//                    predicates.add(criteriaBuilder.or(namePredicate, officePredicate));
//                }
//            }
//
//            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    private Specification<Office> getOfficeSpecification(List<String> keywords) {
//        return (root, query, criteriaBuilder) -> {
//        	List<Predicate> predicates = new ArrayList<>();
//            
//            if (keywords != null && !keywords.isEmpty()) {
//                for (String keyword : keywords) {
//                    Predicate addressPredicate = criteriaBuilder.like(root.get("address"), "%" + keyword + "%");
//                    Predicate countryPredicate = criteriaBuilder.like(root.get("country"), "%" + keyword + "%");
//                    predicates.add(criteriaBuilder.or(addressPredicate, countryPredicate));
//                }
//            }
//
//            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
//        };
//    }

