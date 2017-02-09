package com.tiy.repos;

import com.tiy.entities.BlogPost;
import com.tiy.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dbash on 2/9/2017.
 */
public interface UserRepo extends CrudRepository<User, Long> {
}
