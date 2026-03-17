package com.microservicios.companies_crud.entities;

import com.microservicios.companies_crud.enums.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Data
//Se usa serializable para mandarla como un Json
public class WebSite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(columnDefinition = "category")
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private String description;
}
