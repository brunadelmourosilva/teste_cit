package com.brunadelmouro.ct_teste.controller;

import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.model.dto.PizzaRequestDTO;
import com.brunadelmouro.ct_teste.model.dto.PizzaResponseDTO;
import com.brunadelmouro.ct_teste.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @PostMapping(value = "/savePizza")
    public ResponseEntity<PizzaResponseDTO> savePizza(@Valid @RequestBody PizzaRequestDTO pizzaRequestDTO){
        Pizza pizzaEntity = pizzaService.convertDtoToEntity(pizzaRequestDTO);
        pizzaService.savePizza(pizzaEntity);

        return ResponseEntity.ok().body(pizzaService.convertEntityToResponseDto(pizzaEntity));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PizzaResponseDTO> findPizzaById(@PathVariable Integer id){
        Pizza pizza = pizzaService.findPizzaById(id);

        return ResponseEntity.ok().body(pizzaService.convertEntityToResponseDto(pizza));
    }

    //implementar busca paginada
//    @GetMapping
//    public ResponseEntity<List<PizzaResponseDTO>> findPizzas(){
//        List<Pizza> pizzas = pizzaService.findPizzas();
//
//        return ResponseEntity.ok().body(pizzaService.convertEntityToResponseDto(pizzas));
//    }

    @DeleteMapping("/deletePizza/{id}")
    public void deletePizzaById(@PathVariable Integer id) {
        Pizza pizza = pizzaService.findPizzaById(id);

        pizzaService.deletePizza(pizza);
    }
}
