package com.microservicios.companies_crud.services;

import com.microservicios.companies_crud.entities.Company;

public interface CompanyServices {
    Company create(Company company);
    Company readByName(String name);
    Company update(Company company, String name);
    void delete(String name);
}
