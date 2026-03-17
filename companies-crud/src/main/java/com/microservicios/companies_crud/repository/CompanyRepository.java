package com.microservicios.companies_crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservicios.companies_crud.entities.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);

}
