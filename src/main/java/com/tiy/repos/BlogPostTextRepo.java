package com.tiy.repos;

import com.tiy.entities.BlogPostText;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dbash on 2/9/2017.
 */
public interface BlogPostTextRepo extends CrudRepository<BlogPostText, Long> {
}
