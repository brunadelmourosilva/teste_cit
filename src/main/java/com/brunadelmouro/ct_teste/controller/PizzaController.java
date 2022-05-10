package com.brunadelmouro.ct_teste.controller;

import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @PostMapping(value = "/savePizza")
    public ResponseEntity<Pizza> savePizza(@RequestBody Pizza pizza){
        pizzaService.savePizza(pizza);

        return ResponseEntity.ok().body(pizza);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pizza> findPizzaById(@PathVariable Integer id){
        Pizza pizza = pizzaService.findPizzaById(id);

        return ResponseEntity.ok().body(pizza);
    }

    //implementar busca paginada
    @GetMapping
    public ResponseEntity<List<Pizza>> findPizzas(){

        return ResponseEntity.ok().body(pizzaService.findPizzas());
    }

    @DeleteMapping("/{id}")
    public void deletePizzaById(@PathVariable Integer id) {
        Pizza pizza = pizzaService.findPizzaById(id);

        pizzaService.deletePizza(pizza);
    }
}
