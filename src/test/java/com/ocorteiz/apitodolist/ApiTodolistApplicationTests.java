package com.ocorteiz.apitodolist;

import com.ocorteiz.apitodolist.entity.Todo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTodolistApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSucess() {
		var todo = new Todo("Tafera 1", "Descrição", 7, false);

		webTestClient
				.post()
				.uri("/todo")
				.bodyValue(todo)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$").isArray()
				.jsonPath("$.length()").isEqualTo(1)
				.jsonPath("$[0].nome").isEqualTo(todo.getNome())
				.jsonPath("$[0].descricao").isEqualTo(todo.getDescricao())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade())
				.jsonPath("$[0].realizado").isEqualTo(todo.getRealizado());
	}

	@Test
	void testCreateTodoFailure(){
		webTestClient
				.post()
				.uri("/todo")
				.bodyValue(new Todo("", "", 0, false))
				.exchange()
				.expectStatus().isBadRequest();
	}

}
