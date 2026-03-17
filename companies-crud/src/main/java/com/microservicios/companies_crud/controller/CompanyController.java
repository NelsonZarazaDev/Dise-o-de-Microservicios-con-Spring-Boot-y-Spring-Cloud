package com.microservicios.companies_crud.controller;

import com.microservicios.companies_crud.entities.Company;
import com.microservicios.companies_crud.services.CompanyService;
import com.microservicios.companies_crud.services.CompanyServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(path = "company")
@Slf4j
@Tag(name = "Companies resource")
public class CompanyController {

    private final CompanyService companyService;

    @Operation(summary = "Get a company given a company name")
    @GetMapping(path = "{name}")
    public ResponseEntity<Company> getCompany(@PathVariable String name) {
        log.info("Get: company {}", name);
//        try {
//            Thread.sleep(7000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return ResponseEntity.ok(this.companyService.readByName(name));
    }

    @Operation(summary = "Post in DB a company given a company from body")
    @PostMapping
    public ResponseEntity<Company> postCompany(@RequestBody Company company) {
        log.info("Post company {}", company.getName());
        return ResponseEntity.created(URI.create(this.companyService.create(company).getName())).build();
    }

    @Operation(summary = "Update in DB a company given a company from body")
    @PutMapping(path = "{name}")
    public ResponseEntity<Company> putCompany(@RequestBody Company company, @PathVariable String name) {
        log.info("Put company {}", name);
        return ResponseEntity.ok(this.companyService.update(company, name));
    }

    @Operation(summary = "Delete in DB a company given a company name")
    @DeleteMapping(path = "{name}")
//    El ? significa que puedo regresar company, un tipo boold, string etc
    public ResponseEntity<?> deleteCompany(@PathVariable String name) {
        this.companyService.delete(name);
        return ResponseEntity.noContent().build();
    }
}
