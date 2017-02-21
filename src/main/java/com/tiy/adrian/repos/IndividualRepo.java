package com.tiy.adrian.repos;

import com.tiy.adrian.model.Event;
import com.tiy.adrian.model.Individual;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by dbash on 2/12/2017.
 */
public interface IndividualRepo extends CrudRepository<Individual, Long> {
}
