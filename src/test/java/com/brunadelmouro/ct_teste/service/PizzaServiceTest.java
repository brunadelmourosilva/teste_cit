package com.brunadelmouro.ct_teste.service;

import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.repository.PizzaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PizzaServiceTest {

    @Autowired
    PizzaService pizzaService;

    @MockBean
    PizzaRepository pizzaRepository;

    @Test
    @DisplayName("Should save a Pizza")
    public void savePizzaTest(){
        Pizza pizza = createNewPizza();

        Mockito.when(pizzaRepository.existsByName(Mockito.anyString())).thenReturn(false);

        assertThat(pizza.getId()).isEqualTo(1);
        assertThat(pizza.getName()).isEqualTo("Quatro queijos");
        assertThat(pizza.getDescription()).isEqualTo("Pizza de quatro queijos com mussarela, parmesão, catupiry e provolone");
        assertThat(pizza.getPrice()).isEqualTo(27.20);
    }

    @Test
    @DisplayName("Should return a Pizza by id")
    public void findPizzaByIdTest(){
        Pizza pizza = createNewPizza();

        Mockito.when(pizzaRepository.findById(1)).thenReturn(Optional.of(pizza));

        assertThat(pizzaService.findPizzaById(1)).isEqualTo(pizza);
    }

    @Test
    @DisplayName("Should filter Pizza by page")
    public void findPizzaByPageTest() {
        Pizza pizza = createNewPizza();
        PageRequest pageRequest = PageRequest.of(0,10);

        List<Pizza> listPizza = Arrays.asList(pizza);
        Page<Pizza> page = new PageImpl<>(Arrays.asList(pizza),
                PageRequest.of(0,10), 1);

        Mockito.when(pizzaRepository.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class)))
                .thenReturn(page);

        Page<Pizza> result = pizzaService.findPizzaPage(pizza, pageRequest);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(listPizza);
        assertThat(result.getPageable().getPageNumber()).isEqualTo(0);
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }

    @Test
    @DisplayName("Should delete a Pizza")
    public void deletePizzaTest(){
        Pizza pizza = createNewPizza();

        assertDoesNotThrow(() -> pizzaService.deletePizza(pizza));

        verify(pizzaRepository, times(1)).delete(pizza);
    }

    public Pizza createNewPizza(){
        return new Pizza(
                1,
                "Quatro queijos",
                "Pizza de quatro queijos com mussarela, parmesão, catupiry e provolone",
                27.20);
    }

}
