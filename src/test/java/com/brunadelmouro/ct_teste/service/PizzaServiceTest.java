package com.brunadelmouro.ct_teste.service;

import com.brunadelmouro.ct_teste.exceptions.ObjectNotFoundException;
import com.brunadelmouro.ct_teste.model.Pizza;
import com.brunadelmouro.ct_teste.repository.PizzaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    @InjectMocks
    PizzaService pizzaService;

    @Mock
    PizzaRepository pizzaRepository;

    Pizza pizzaQueijo;
    Pizza pizzaGoiaba;
    List<Pizza> pizzas;

    @BeforeEach
    void setUp() {
        pizzaQueijo = new Pizza(
                1,
                "Quatro queijos",
                "Pizza de quatro queijos com mussarela, parmesão, catupiry e provolone",
                27.20);

        pizzaGoiaba = new Pizza(
                2,
                "Goiabada",
                "Pizza de queijo com goiabada",
                21.20);

        pizzas = Arrays.asList(pizzaGoiaba, pizzaQueijo);
    }

    @Test
    @DisplayName("Should save a Pizza")
    void savePizzaWithSuccessTest(){
        //given
        given(pizzaRepository.save(any(Pizza.class))).willReturn(pizzaQueijo);

        //when
        final var response = pizzaService.savePizza(pizzaQueijo);

        //then
        then(pizzaRepository).should().findAll();
        then(pizzaRepository).should().save(pizzaQueijo);

        assertEquals(27.20, response.getPrice());
    }

    @Test
    @DisplayName("Should return a Pizza by id")
    void findPizzaByIdWithSuccessTest(){
        //given
        given(pizzaRepository.findById(anyInt())).willReturn(Optional.of(pizzaGoiaba));

        //when
        final var response = pizzaService.findPizzaById(anyInt());

        //then
        then(pizzaRepository).should().findById(anyInt());

        assertEquals(21.20, response.getPrice());
    }

    @Test
    @DisplayName("Should an ObjectNotFoundException when id was not found")
    void findPizzaByIdWhenIdWasNotFoundTest(){
        //given
        given(pizzaRepository.findById(anyInt())).willReturn(Optional.empty());

        //when
        assertThrows(ObjectNotFoundException.class, () -> pizzaService.findPizzaById(34));

        //then
        then(pizzaRepository).should().findById(anyInt());
    }

    //TODO refactor test class
    @Test
    @DisplayName("Should filter Pizza by page")
    void findPizzaByPageTest() {
        PageRequest pageRequest = PageRequest.of(0,10);

        List<Pizza> listPizza = Arrays.asList(pizzaQueijo);
        Page<Pizza> page = new PageImpl<>(Arrays.asList(pizzaQueijo),
                PageRequest.of(0,10), 1);

        Mockito.when(pizzaRepository.findAll(any(Example.class), any(PageRequest.class)))
                .thenReturn(page);

        Page<Pizza> result = pizzaService.findPizzaPage(pizzaQueijo, pageRequest);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent()).isEqualTo(listPizza);
        assertThat(result.getPageable().getPageNumber()).isZero();
        assertThat(result.getPageable().getPageSize()).isEqualTo(10);
    }

    @Test
    @DisplayName("Should delete a Pizza")
    void deletePizzaTest(){
        assertDoesNotThrow(() -> pizzaService.deletePizza(pizzaQueijo));

        verify(pizzaRepository, times(1)).delete(pizzaQueijo);
    }

}
