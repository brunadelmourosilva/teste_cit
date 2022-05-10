package com.brunadelmouro.ct_teste.controller;

import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.model.dto.PizzaRequestDTO;
import com.brunadelmouro.ct_teste.model.dto.PizzaResponseDTO;
import com.brunadelmouro.ct_teste.service.PizzaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @ApiOperation(value = "Cadastrar pizza")
    @PostMapping(value = "/savePizza")
    public ResponseEntity<PizzaResponseDTO> savePizza(@Valid @RequestBody PizzaRequestDTO pizzaRequestDTO){
        Pizza pizzaEntity = pizzaService.convertDtoToEntity(pizzaRequestDTO);
        pizzaService.savePizza(pizzaEntity);

        return ResponseEntity.ok().body(pizzaService.convertEntityToResponseDto(pizzaEntity));
    }

    @ApiOperation(value = "Listar informações de uma pizza pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<PizzaResponseDTO> findPizzaById(@PathVariable Integer id){
        Pizza pizza = pizzaService.findPizzaById(id);

        return ResponseEntity.ok().body(pizzaService.convertEntityToResponseDto(pizza));
    }

    @ApiOperation(value = "Listar informações de pizzas por paginação")
    @GetMapping
    public Page<PizzaResponseDTO> findPizzasByPage(Pizza pizza, Pageable pageRequest){
        Page<Pizza> result = pizzaService.findPizzaPage(pizza, pageRequest);

        List<PizzaResponseDTO> list = result.getContent()
                .stream()
                .map(entity -> pizzaService.convertEntityToResponseDto(entity))
                .collect(Collectors.toList());

        return new PageImpl<>(list, pageRequest, result.getTotalElements());
    }

    @ApiOperation(value = "Deletar pizza")
    @DeleteMapping("/deletePizza/{id}")
    public void deletePizzaById(@PathVariable Integer id) {
        Pizza pizza = pizzaService.findPizzaById(id);

        pizzaService.deletePizza(pizza);
    }
}
