package com.tiy;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.tiy.adrian.model.Event;
import com.tiy.adrian.mongo.DatabaseUtils;
import com.tiy.adrian.repos.EventRepo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by dbash on 2/13/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoDBUnitTests {

    static MongoClient mongoClient;
    static DB adrianDB;

    @Autowired
    EventRepo eventRepo;

    @BeforeClass
    public static void init() {
        if (mongoClient == null) {
            mongoClient = new MongoClient(new MongoClientURI(DatabaseUtils.DB_CONNECTION_URL));
            adrianDB = mongoClient.getDB("AdrianTestDB");
        }
    }

    @AfterClass
    public static void cleanUp() {
        if (mongoClient != null) {
//            adrianDB.dropDatabase();
            mongoClient.close();
        }
    }

    @Test
    public void testMongoConnection() {
        DBCollection eventCollection = adrianDB.getCollection("events");
        int numBefore = eventCollection.find().count();
        assertNotNull(eventCollection);
        Event event = Event.createTestEvents().get(0);
        eventCollection.insert(DatabaseUtils.toDBOjbect(event));
        int numAfter = eventCollection.find().count();
        assertEquals(numBefore+1, numAfter);
    }

    @Test
    public void testSpringMongo() {
        List<Event> events = Event.createTestEvents();
        Event event = events.get(0);
        eventRepo.save(event);
    }
}
