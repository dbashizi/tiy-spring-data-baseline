package com.tiy.repos;

import com.tiy.entities.BlogPost;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dbash on 2/9/2017.
 */
public interface BlogPostRepo extends CrudRepository<BlogPost, Long> {
}
