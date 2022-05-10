package com.brunadelmouro.ct_teste.repository;

import com.brunadelmouro.ct_teste.model.Pizza;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
public class PizzaRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    PizzaRepository pizzaRepository;

    @Test
    @DisplayName("Should save a Pizza with success")
    public void savePizzaTest() {
        Pizza pizza = createNewPizza();

        Pizza savedPizza = pizzaRepository.save(pizza);

        assertThat(savedPizza.getId()).isNotNull();
    }

    @Test
    @DisplayName("Should return a Pizza by id with success")
    public void findPizzaByIdTest() {
        Pizza pizza = createNewPizza();

        testEntityManager.persist(pizza);

        Optional<Pizza> foundPizza = pizzaRepository.findById(pizza.getId());
        System.out.println(foundPizza);

        assertThat(foundPizza.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should delete a Pizza with success")
    public void deletePizza() {
        Pizza pizza = createNewPizza();

        testEntityManager.persist(pizza);

        Pizza foundPizza = testEntityManager.find(Pizza.class, pizza.getId());
        pizzaRepository.delete(foundPizza);
        System.out.println(foundPizza);

        Pizza deletedPizza = testEntityManager.find(Pizza.class, pizza.getId());
        System.out.println(deletedPizza); //null

        assertThat(deletedPizza).isNull();
    }

    public Pizza createNewPizza(){
        return new Pizza(
                null,
                "Quatro queijos",
                "Pizza de quatro queijos com mussarela, parmesão, catupiry e provolone",
                27.20);
    }

}
