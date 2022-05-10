package com.brunadelmouro.ct_teste.repository;

import com.brunadelmouro.ct_teste.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
}
