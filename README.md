![image](https://user-images.githubusercontent.com/61791877/167641351-a92111d3-00ed-41bf-a461-ba49b4015a25.png)



# Teste técnico - CI&T

---

### Status
>Concluído ✔️

---

### Tecnologias

* Java 11
* Spring Boot 2.6.7
* Spring Data JPA
* Maven
* MySQL
* H2 Database
* JUnit
* Mockito
* Swagger

---

### Como utilizar a aplicação?

* 1- Clone o projeto para o diretório desejado
* 2- Abra um terminal (git bash, de preferência) na raiz do projeto
* 3- Execute os seguintes comandos:
```
mvn clean install
mvn clean package
docker-compose up -d
```

* 4- Rode a aplicação com o IntelliJ
* 5- Acesse os endpoints da aplicação, via swagger: http://localhost:8080/swagger-ui/#/

---

### Banco de dados com Docker

---

### Features

* **Exceções personalizadas**: O código foi programado para lançar exceções descritivas, que auxiliam o programador a encontrar a origem do problema.

![img.png](img.png)

![img_2.png](img_2.png)

* **Lista de exceções referentes à validação**: Ao tentar realizar a entrada de dados incompletos/incorretos, o sistema retorna uma lista de exceções personalizadas.

![img_5.png](img_5.png)

* **Testes unitários**: Com o objetivo de manter a integridade das funções, realizei alguns testes nas seguintes camadas: **repository** e **servcice**

![img_6.png](img_6.png)

![img_3.png](img_3.png)
