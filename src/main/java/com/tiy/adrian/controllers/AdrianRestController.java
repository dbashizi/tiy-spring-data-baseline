package com.tiy.adrian.controllers;

import com.tiy.adrian.model.*;
import com.tiy.adrian.repos.EventRepo;
import com.tiy.adrian.repos.IndividualRepo;
import com.tiy.adrian.repos.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
@RestController
public class AdrianRestController {

    @Autowired
    private EventRepo eventRepo;
    @Autowired
    private IndividualRepo individualRepo;
    @Autowired
    private OrganizationRepo organizationRepo;

    @RequestMapping(path = "/login-individual.json", method = RequestMethod.POST)
    public IndividualResponse loginIndividual(HttpSession session, @RequestBody LoginRequest loginRequest) {
        Individual individual = individualRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (individual == null) {
            return IndividualResponse.createErrorIndividualResponse("Invalid email or password", 1);
        } else {
            return IndividualResponse.createIndividualResponse(individual, eventRepo.findByUserIdsIn(Arrays.asList(individual.getId())));
        }
    }

    @RequestMapping(path = "/register-individual.json", method = RequestMethod.POST)
    public IndividualResponse registerIndividual(HttpSession session, @RequestBody RegistrationRequestIndividual registrationRequestIndividual) {
        Individual individual = new Individual();
        individual.setEmail(registrationRequestIndividual.getEmail());
        individual.setFirstName(registrationRequestIndividual.getFirstName());
        individual.setLastName(registrationRequestIndividual.getLastName());
        individual.setPassword(registrationRequestIndividual.getPassword());
//        individual.setId(registrationRequestIndividual.getId());
        individualRepo.save(individual);

//        return IndividualResponse.createTestIndividualResponse();
        return IndividualResponse.createIndividualResponse(individual, eventRepo.findByUserIdsIn(Arrays.asList(individual.getId())));
    }

    @RequestMapping(path = "/login-organization.json", method = RequestMethod.POST)
    public OrganizationResponse loginOrganization(HttpSession session, @RequestBody LoginRequest loginRequest) {
        Organization organization = organizationRepo.findByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (organization == null) {
            return OrganizationResponse.createErrorOrganizationResponse("Invalid email or password", 1);
        } else {
            return OrganizationResponse.createOrganizationResponse(organization, eventRepo.findByUserIdsIn(Arrays.asList(organization.getId())));
        }
    }

    @RequestMapping(path = "/register-organization.json", method = RequestMethod.POST)
    public OrganizationResponse registerOrganization(HttpSession session, @RequestBody RegistrationRequestOrganization registrationRequestOrganization) {
        Organization organization = new Organization();
        organization.setEmail(registrationRequestOrganization.getEmail());
        organization.setPassword(registrationRequestOrganization.getPassword());
        organization.setAddress(registrationRequestOrganization.getAddress());
        organization.setCity(registrationRequestOrganization.getCity());
        organization.setName(registrationRequestOrganization.getName());
        organization.setState(registrationRequestOrganization.getState());
        organization.setZip(registrationRequestOrganization.getZip());
        organizationRepo.save(organization);

        return OrganizationResponse.createOrganizationResponse(organization, eventRepo.findByUserIdsIn(Arrays.asList(organization.getId())));
    }

    @RequestMapping(path = "/event-list.json", method = RequestMethod.GET)
    public List<Event> eventList(HttpSession session) {
        return eventRepo.findAll();
    }

    @RequestMapping(path = "/join-event.json", method = RequestMethod.POST)
    public EventResponse joinEvent(HttpSession session, @RequestBody EventRequest eventRequest) throws Exception {
        Event event = eventRepo.findOne(eventRequest.getEventId());
        if (event == null) {
            throw new Exception("Unknown event with ID " + eventRequest.getEventId());
        }
        Individual individual = individualRepo.findOne(eventRequest.getUserId());
        if (individual == null) {
            throw new Exception("Unknown individual with user ID " + eventRequest.getUserId());
        }
        event.addUserId(individual.getId());
        eventRepo.save(event);
        return EventResponse.createEventResponse(eventRepo.findByUserIdsIn(Arrays.asList(individual.getId())));
    }

    @RequestMapping(path = "/events-for-individual.json", method = RequestMethod.POST)
    public EventResponse eventsForIndividual(HttpSession session, @RequestBody HostEventRequest hostEventRequest) {
        List<Event> hostedEvents = eventRepo.findByHostId(hostEventRequest.getUserId());
        return EventResponse.createEventResponse(eventRepo.findByUserIdsIn(Arrays.asList(hostEventRequest.getUserId())));
    }

    @RequestMapping(path = "/create-event.json", method = RequestMethod.POST)
    public EventResponse createEvent(HttpSession session, @RequestBody Event event) {
//        return EventResponse.creasteTestEventResponse();
        System.out.println("Start time: " + event.getStartTime().toString());
        System.out.println("End time: " + event.getEndTime().toString());
        eventRepo.save(event);
        return EventResponse.createEventResponse(eventRepo.findAll());
    }

    @RequestMapping(path = "/edit-event.json", method = RequestMethod.POST)
    public EventResponse editEvent(HttpSession session, @RequestBody Event event) {
        eventRepo.save(event);
        return EventResponse.createEventResponse(eventRepo.findAll());
    }

    @RequestMapping(path = "/delete-event.json", method = RequestMethod.POST)
    public EventResponse deleteEvent(HttpSession session, @RequestBody EventRequest eventRequest) {
        Event event = eventRepo.findOne(eventRequest.getEventId());
        eventRepo.delete(event);
        return EventResponse.createEventResponse(eventRepo.findAll());
    }

    @RequestMapping(path = "/leave-event.json", method = RequestMethod.POST)
    public EventResponse leaveEvent(HttpSession session, @RequestBody EventRequest eventRequest) throws Exception {
        Event event = eventRepo.findOne(eventRequest.getEventId());
        if (event == null) {
            throw new Exception("Unknown event with ID " + eventRequest.getEventId());
        }
        List<String> userIds = event.getUserIds();
        List<String> newUserIds = new ArrayList<String>();
        for (String userId : userIds) {
            if (!userId.equals(eventRequest.getUserId())) {
                newUserIds.add(userId);
            }
        }
        event.setUserIds(newUserIds);
        eventRepo.save(event);
        return EventResponse.createEventResponse(eventRepo.findAll());
    }

    @RequestMapping(path = "/edit-individual.json", method = RequestMethod.POST)
    public IndividualResponse editIndividual(HttpSession session, @RequestBody EditIndividualRequest editIndividualRequest) {
        Individual individual = individualRepo.findOne(editIndividualRequest.getUserId());
        individual.setEmail(editIndividualRequest.getEmail());
        individual.setFirstName(editIndividualRequest.getFirstName());
        individual.setLastName(editIndividualRequest.getLastName());
        individual.setPassword(editIndividualRequest.getPassword());
        individualRepo.save(individual);
        return IndividualResponse.createIndividualResponse(individual, eventRepo.findByUserIdsIn(Arrays.asList(individual.getId())));
    }

    @RequestMapping(path = "/edit-organization.json", method = RequestMethod.POST)
    public OrganizationResponse editOrganization(HttpSession session, @RequestBody EditOrganizationRequest editOrganizationRequest) {
        Organization organization = organizationRepo.findOne(editOrganizationRequest.getId());
        organization.setEmail(editOrganizationRequest.getEmail());
        organization.setPassword(editOrganizationRequest.getPassword());
        organization.setAddress(editOrganizationRequest.getAddress());
        organization.setCity(editOrganizationRequest.getCity());
        organization.setName(editOrganizationRequest.getName());
        organization.setState(editOrganizationRequest.getState());
        organization.setZip(editOrganizationRequest.getZip());

        return OrganizationResponse.createOrganizationResponse(organization, eventRepo.findByUserIdsIn(Arrays.asList(organization.getId())));
    }

}
