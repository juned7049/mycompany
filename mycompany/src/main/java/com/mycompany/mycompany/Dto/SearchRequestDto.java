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

			public SearchRequestDto() {
				super();
				// TODO Auto-generated constructor stub
			}

			public SearchRequestDto(List<Employee> employees) {
				super();
				this.employees = employees;
			}

			public List<Employee> getEmployees() {
				return employees;
			}

			public void setEmployees(List<Employee> employees) {
				this.employees = employees;
			}
	
	}

