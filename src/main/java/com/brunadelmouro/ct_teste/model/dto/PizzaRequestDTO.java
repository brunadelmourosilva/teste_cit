package com.brunadelmouro.ct_teste.model.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PizzaRequestDTO {

    @NotNull(message = "The field NAME cannot be null")
    @Length(min = 3, max = 100, message = "The field NAME needs to be between 3 and 100 characters")
    private String name;

    @NotNull(message = "The field DESCRIPTION cannot be null")
    @Length(min = 10, max = 150, message = "The field NAME needs to be between 10 and 150 characters")
    private String description;

    @NotNull(message = "The field PRICE cannot be null")
    @DecimalMax("100.0") @DecimalMin("15.0")
    private Double price;

    public PizzaRequestDTO() {
    }

    public PizzaRequestDTO(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
