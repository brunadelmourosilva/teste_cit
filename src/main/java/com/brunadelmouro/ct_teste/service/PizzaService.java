package com.brunadelmouro.ct_teste.service;

import com.brunadelmouro.ct_teste.exceptions.ObjectNotFoundException;
import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.model.dto.PizzaRequestDTO;
import com.brunadelmouro.ct_teste.model.dto.PizzaResponseDTO;
import com.brunadelmouro.ct_teste.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;


    public Pizza savePizza(Pizza pizza) {
        validatePizzaDoesNotExistByName(pizza.getName());

        return pizzaRepository.save(pizza);
    }

    public Pizza findPizzaById(Integer id) {
        Optional<Pizza> pizza = pizzaRepository.findById(id);

        return pizza.orElseThrow(() -> new ObjectNotFoundException("Pizza not found."));
    }

    public Page<Pizza> findPizzaPage(Pizza filter, Pageable pageRequest) {
        Example<Pizza> example = Example.of(filter,
                ExampleMatcher
                        .matching()
                        .withIgnoreCase()
                        .withIgnoreNullValues()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return pizzaRepository.findAll(example, pageRequest);
    }

    public void deletePizza(Pizza pizza) {
        validatePizzaExists(pizza);

        this.pizzaRepository.delete(pizza);
    }

    public void validatePizzaDoesNotExistByName(String name){
        pizzaRepository.findAll().forEach(
                x -> {
                    String comparison = x.getName().toUpperCase();
                    if(name.toUpperCase().equals(comparison))
                        throw new IllegalArgumentException("Pizza " + name + " is already registered.");
                });
    }

    public Pizza validatePizzaExists(Pizza pizza){
        if (pizza == null || pizza.getId() == null) {
            throw new IllegalArgumentException("Pizza id cannot be null.");
        }
        return pizza;
    }

    public Pizza convertDtoToEntity(PizzaRequestDTO pizzaRequestDTO) {
        return new Pizza(
                null,
                pizzaRequestDTO.getName(),
                pizzaRequestDTO.getDescription(),
                pizzaRequestDTO.getPrice());
    }

    public PizzaResponseDTO convertEntityToResponseDto(Pizza pizzaEntity) {
        return new PizzaResponseDTO(
                pizzaEntity.getName(),
                pizzaEntity.getDescription(),
                pizzaEntity.getPrice()
        );
    }
}
