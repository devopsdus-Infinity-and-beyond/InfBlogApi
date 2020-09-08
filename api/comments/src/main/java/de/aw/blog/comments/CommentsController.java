package de.aw.blog.comments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/posts/{postId}/comments")
public class CommentsController {
	
	@Autowired
	CommentsService commentsService;

	@GetMapping
	public List<Comment> getComments(@PathVariable long postId) {
		return commentsService.getCommentsByPostId(postId);
		
	}
	
}
