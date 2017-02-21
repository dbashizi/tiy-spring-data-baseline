package com.tiy.adrian.repos;

import com.tiy.adrian.model.Event;
import com.tiy.adrian.model.Organization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dbash on 2/12/2017.
 */
public interface EventRepo extends MongoRepository<Event, Long> {
}
