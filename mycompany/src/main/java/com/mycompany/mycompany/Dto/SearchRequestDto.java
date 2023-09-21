package com.mycompany.mycompany.Dto;


	import java.util.List;

import com.mycompany.mycompany.entites.Department;
import com.mycompany.mycompany.entites.Employee;
import com.mycompany.mycompany.entites.Office;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


	@Data
	@AllArgsConstructor
	@NoArgsConstructor

		public class SearchRequestDto {
		  
			private List<Employee> employees;
		    private List<Department> departments;
		    private List<Office> offices;
			public SearchRequestDto() {
				super();
				// TODO Auto-generated constructor stub
			}
			public SearchRequestDto(List<Employee> employees, List<Department> departments, List<Office> offices) {
				super();
				this.employees = employees;
				this.departments = departments;
				this.offices = offices;
			}
			public List<Employee> getEmployees() {
				return employees;
			}
			public void setEmployees(List<Employee> employees) {
				this.employees = employees;
			}
			public List<Department> getDepartments() {
				return departments;
			}
			public void setDepartments(List<Department> departments) {
				this.departments = departments;
			}
			public List<Office> getOffices() {
				return offices;
			}
			public void setOffices(List<Office> offices) {
				this.offices = offices;
			}
			@Override
			public String toString() {
				return "SearchRequestdto [employees=" + employees + ", departments=" + departments + ", offices="
						+ offices + "]";
			}

		    // Constructors, getters, setters, etc.
		    
		    
		}

