package de.aw.blog.comments;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@AutoConfigurationPackage(basePackageClasses = CommentsControllerMvcTest.class)
@SpringBootTest(classes = {
		CommentsControllerMvcTest.TestApplication.class,
		CommentsController.class, CommentsService.class, CommentsRepository.class
	})
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=validate"
})
public class CommentsControllerMvcTest {
	
	@Autowired
	MockMvc mockMvc;

	@DisplayName("When /posts/1/comments is called Then 2 comments are returned")
	@Test
	public void returnComments() throws Exception {
		mockMvc.perform(
				get("/posts/1/comments")
			).
		andDo(print()).
		andExpect(status().isOk()).
		andExpect(content().json("[{\"id\":1,\"postId\":1,\"text\":\"A\",\"username\":\"A\"},{\"id\":2,\"postId\":1,\"text\":\"B\",\"username\":\"B\"}]"));

	}
	
	@SpringBootApplication
	public static class TestApplication {
		public static void main(String[] args) throws Exception {
			SpringApplication.run(TestApplication.class, args);
		}
	}
}
