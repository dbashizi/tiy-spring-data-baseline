package com.tiy.repos;

import com.tiy.entities.PostComment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dbash on 2/9/2017.
 */
public interface PostCommentRepo extends CrudRepository<PostComment, Long> {
}
