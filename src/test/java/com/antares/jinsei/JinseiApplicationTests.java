package com.antares.jinsei;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.antares.jinsei.controller.HelloController;

@WebMvcTest(controllers = HelloController.class)
@WithMockUser(username = "alice", password = "12345")
class JinseiApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void testHello() throws Exception {
		mvc.perform(get("/hello"))
				.andExpect(content().string("Hello!"))
				.andExpect(status().isOk());
	}

}
