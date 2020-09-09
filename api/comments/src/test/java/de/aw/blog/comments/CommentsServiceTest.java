package de.aw.blog.comments;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CommentsServiceTest {
	
	@Mock
	CommentsRepository commmentsRepository;
	
	@InjectMocks
	CommentsService unitUnderTest;

	@DisplayName("When getCommentsByPostId(1) is called Then 3 comments are returned")
	@Test
	public void returnComments() {
		
		when(commmentsRepository.findAll()).thenReturn(Arrays.asList(
				createComment(1),
				createComment(1),
				createComment(1),
				createComment(2)
				));
		List<Comment> resultToCheck = unitUnderTest.getCommentsByPostId(1);
		assertThat(resultToCheck).hasSize(3);
	}
	
	@DisplayName("When no comments are found in database an empty list is returned")
	@Test
	public void returnNoCommentsOnEmptyDatabase() {
		
		when(commmentsRepository.findAll()).thenReturn(Collections.emptyList());
		List<Comment> resultToCheck = unitUnderTest.getCommentsByPostId(1);
		assertThat(resultToCheck).isNotNull();
		assertThat(resultToCheck).hasSize(0);
	}
	
	private Comment createComment(long postId) {
		Comment comment = new Comment();
		comment.setId(423423434);
		comment.setPostId(postId);
		comment.setText("i like your blod post");
		comment.setUsername("Kasper Hauser");
		return comment;
	}
}
