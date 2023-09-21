package com.mycompany.mycompany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.mycompany.mycompany.entites.Office;

public interface OfficeRepository extends JpaRepository<Office, Long> , JpaSpecificationExecutor<Office> {

}
