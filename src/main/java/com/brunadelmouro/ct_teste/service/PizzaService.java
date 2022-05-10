package com.brunadelmouro.ct_teste.service;

import com.brunadelmouro.ct_teste.exceptions.ObjectNotFoundException;
import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Pizza> obj = pizzaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException("Pizza not found."));
    }

    //aplicar Pageable
    public List<Pizza> findPizzas() {
        return pizzaRepository.findAll();
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
                        throw new IllegalArgumentException("Pizza already exists.");
                });
    }

    public Pizza validatePizzaExists(Pizza pizza){
        if (pizza == null || pizza.getId() == null) {
            throw new IllegalArgumentException("Pizza id cannot be null.");
        }
        return pizza;
    }
}
