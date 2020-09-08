package de.aw.blog.comments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentsService {
	
	@Autowired
	CommentsRepository commentsRepository;

	public List<Comment> getCommentsByPostId(long postId){
		Iterable<Comment> allComments = commentsRepository.findAll();
		List<Comment> result = new ArrayList<>();
		
		allComments.forEach(
				(Comment comment) -> {
					if(postId == comment.getPostId()) {				
						result.add(comment);
					}
			   }
		);
		
		return result;
		
	}
	
}
