package io.javabrains.springsecurityjwt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Hello)
class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void checkWithoutBearrerToken() throws Exception {
		this.mockMvc.perform(get("/hello")).andExpect(status().isOk());
		
	}

}