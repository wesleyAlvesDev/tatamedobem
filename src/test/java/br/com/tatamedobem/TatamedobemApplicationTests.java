package br.com.tatamedobem;

import br.com.tatamedobem.domain.UserAccessHistory;
import br.com.tatamedobem.repository.UserAccessHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TatamedobemApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserAccessHistoryRepository userAccessHistoryRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void shouldRejectUnauthenticatedStudentsRequest() throws Exception {
		mockMvc.perform(get("/api/students"))
				.andExpect(status().isForbidden());
	}

	@Test
	void shouldAuthenticateAndListStudents() throws Exception {
		String token = mockMvc.perform(post("/api/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.header("User-Agent", "MockMvc")
						.header("X-Forwarded-For", "203.0.113.10")
						.content("""
								{
								  "cpf": "11122233344",
								  "password": "123456"
								}
								"""))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.token").isNotEmpty())
				.andReturn()
				.getResponse()
				.getContentAsString()
				.replaceAll(".*\"token\":\"([^\"]+)\".*", "$1");

		mockMvc.perform(get("/api/students")
						.header("Authorization", "Bearer " + token))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray());

		UserAccessHistory history = userAccessHistoryRepository.findByUserCpf("11122233344").get(0);
		assertThat(history.getIpAddress()).isEqualTo("203.0.113.10");
		assertThat(history.getUserAgent()).isEqualTo("MockMvc");
		assertThat(history.getSuccess()).isTrue();
	}

}
