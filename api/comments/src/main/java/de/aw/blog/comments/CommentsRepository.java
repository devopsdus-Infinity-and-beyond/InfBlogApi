package de.aw.blog.comments;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends PagingAndSortingRepository<Comment, Long>{

	@Query("select u from Comment u where u.postId = :id")
	List<Comment> findAllByPostId(@Param("id") String postId);
}
