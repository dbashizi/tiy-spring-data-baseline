package com.tiy.adrian.repos;

import com.tiy.adrian.model.Event;
import com.tiy.adrian.model.Individual;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by dbash on 2/12/2017.
 */
public interface IndividualRepo extends MongoRepository<Individual, String> {
    Individual findByEmailAndPassword(String email, String password);
}
