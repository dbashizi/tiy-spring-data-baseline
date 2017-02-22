package com.tiy;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.tiy.adrian.model.Event;
import com.tiy.adrian.model.Individual;
import com.tiy.adrian.model.Organization;
import com.tiy.adrian.mongo.DatabaseUtils;
import com.tiy.adrian.repos.EventRepo;
import com.tiy.adrian.repos.IndividualRepo;
import com.tiy.adrian.repos.OrganizationRepo;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
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
    @Autowired
    IndividualRepo individualRepo;
    @Autowired
    OrganizationRepo organizationRepo;

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
        List<Event> hcEvents = Event.createTestEvents();
        Event hcEvent = hcEvents.get(0);
        hcEvent.setId(null);
        eventRepo.save(hcEvent);
        List<Event> events = eventRepo.findAll();
        for (Event event : events) {
            System.out.println("Event ID: " + event.getId());
        }
    }

    @Test
    public void testEvent() {
        Event event = new Event();
        event.setName("Unit Test Event");
        long eventsBefore = eventRepo.count();
        eventRepo.save(event);
        assertEquals(eventsBefore +1, eventRepo.count());
        assertNotNull(event.getId());

        Individual firstUser = new Individual();
        Individual secondUser = new Individual();
        Individual hostUser = new Individual();
        individualRepo.save(firstUser);
        individualRepo.save(secondUser);
        individualRepo.save(hostUser);
        assertNotNull(firstUser.getId());
        assertNotNull(secondUser.getId());
        assertNotNull(hostUser.getId());
        List<String> userIds = new ArrayList<String>();
        userIds.add(firstUser.getId());
        userIds.add(secondUser.getId());
        event.setUserIds(userIds);
        event.setHostId(hostUser.getId());
        eventRepo.save(event);

        List<Event> eventsByUserId = eventRepo.findByUserIdsIn(userIds);
        assertNotNull(eventsByUserId);
        assertEquals(1, eventsByUserId.size());

        Event secondEvent = new Event();
        secondEvent.setName("Second Unit Test Event");
        secondEvent.setUserIds(userIds);
        eventRepo.save(secondEvent);
        eventsByUserId = eventRepo.findByUserIdsIn(userIds);
        assertEquals(2, eventsByUserId.size());

        List<Event> hostedEvents = eventRepo.findByHostId(hostUser.getId());
        assertNotNull(hostedEvents);
        assertEquals(1, hostedEvents.size());
        assertEquals(event.getId(), hostedEvents.get(0).getId());

        individualRepo.delete(firstUser);
        individualRepo.delete(secondUser);
        individualRepo.delete(hostUser);
        eventRepo.delete(event);
        eventRepo.delete(secondEvent);

        assertEquals(eventsBefore, eventRepo.count());
    }

    @Test
    public void testIndividual() {
        Individual testIndividual = new Individual();
        testIndividual.setFirstName("Test");
        testIndividual.setLastName("Tester");
        testIndividual.setEmail("tester@tiy.com");
        testIndividual.setPassword("password");

        long countBefore = individualRepo.count();
        individualRepo.save(testIndividual);

        assertEquals(countBefore +1, individualRepo.count());
        assertNotNull(testIndividual.getId());

        Individual retrievedInd = individualRepo.findByEmailAndPassword(testIndividual.getEmail(), testIndividual.getPassword());
        assertNotNull(retrievedInd);
        assertEquals(testIndividual.getId(), retrievedInd.getId());

        individualRepo.delete(testIndividual);
        assertEquals(countBefore, individualRepo.count());
    }

    @Test
    public void testOrganization() {
        Organization testOrganization = new Organization();
        testOrganization.setEmail("tester@tiy.com");
        testOrganization.setPassword("password");

        long countBefore = organizationRepo.count();
        organizationRepo.save(testOrganization);

        assertEquals(countBefore +1, organizationRepo.count());
        assertNotNull(testOrganization.getId());

        Organization retrievedInd = organizationRepo.findByEmailAndPassword(testOrganization.getEmail(), testOrganization.getPassword());
        assertNotNull(retrievedInd);
        assertEquals(testOrganization.getId(), retrievedInd.getId());

        organizationRepo.delete(testOrganization);
        assertEquals(countBefore, organizationRepo.count());
    }
}
