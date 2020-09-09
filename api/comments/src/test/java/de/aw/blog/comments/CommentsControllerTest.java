package de.aw.blog.comments;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;


@DataJpaTest
@AutoConfigurationPackage(basePackageClasses = CommentsControllerTest.class)
@ContextConfiguration(classes = {CommentsController.class, CommentsService.class, CommentsRepository.class})
public class CommentsControllerTest {
	
    @Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	CommentsController unitUnderTest;
	
	@BeforeEach
	public void initDB() {
		{
			Comment comment = new Comment();
			comment.setPostId(1);
			comment.setText("a");
			comment.setUsername("user a");
			this.entityManager.persist(comment);
		}
		{
			Comment comment = new Comment();
			comment.setPostId(1);
			comment.setText("b");
			comment.setUsername("user b");
			this.entityManager.persist(comment);
		}
		{
			Comment comment = new Comment();
			comment.setPostId(2);
			comment.setText("c");
			comment.setUsername("user c");
			this.entityManager.persist(comment);
		}
	}

	@DisplayName("When getComments(1) is called Then 2 comments are returned")
	@Test
	public void return2CommentsWhenCallinggetComments1() {
	   
		List<Comment> resultToCheck = unitUnderTest.getComments(1);
		assertThat(resultToCheck).hasSize(2);
		assertThat(resultToCheck.get(0).getText()).isEqualTo("a");
		assertThat(resultToCheck.get(1).getText()).isEqualTo("b");
	}
	
}
