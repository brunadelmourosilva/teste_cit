package com.brunadelmouro.ct_teste.service;

import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.repository.PizzaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;


    public Pizza savePizza(Pizza pizza) {
        //validação
        return pizzaRepository.save(pizza);
    }

    public Pizza findPizzaById(Integer id) {
        Optional<Pizza> obj = pizzaRepository.findById(id);

        return obj.orElseThrow(() -> new ObjectNotFoundException(1, "Pizza not found."));
    }

    //aplicar Pageable
    public List<Pizza> findPizzas() {
        return pizzaRepository.findAll();
    }

    public void deletePizza(Pizza pizza) {
        //validação
        this.pizzaRepository.delete(pizza);
    }
}
