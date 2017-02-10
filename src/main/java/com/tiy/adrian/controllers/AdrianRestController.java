package com.tiy.adrian.controllers;

import com.tiy.adrian.model.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
@RestController
public class AdrianRestController {

    @RequestMapping(path = "/login-individual.json", method = RequestMethod.POST)
    public IndividualResponse loginIndividual(HttpSession session, @RequestBody LoginRequest loginRequest) {
        return IndividualResponse.createTestIndividualResponse();
    }

    @RequestMapping(path = "/register-individual.json", method = RequestMethod.POST)
    public IndividualResponse registerIndividual(HttpSession session, @RequestBody RegistrationRequestIndividual registrationRequestIndividual) {
        return IndividualResponse.createTestIndividualResponse();
    }

    @RequestMapping(path = "/login-organization.json", method = RequestMethod.POST)
    public OrganizationResponse loginOrganization(HttpSession session, @RequestBody LoginRequest loginRequest) {
        return OrganizationResponse.createTestOrganizationResponse();
    }

    @RequestMapping(path = "/register-organization.json", method = RequestMethod.POST)
    public OrganizationResponse registerOrganization(HttpSession session, @RequestBody RegistrationRequestOrganization registrationRequestOrganization) {
        return OrganizationResponse.createTestOrganizationResponse();
    }

    @RequestMapping(path = "/event-list.json", method = RequestMethod.GET)
    public List<Event> eventList(HttpSession session) {
        return Event.createTestEvents();
    }

    @RequestMapping(path = "/join-event.json", method = RequestMethod.POST)
    public EventResponse joinEvent(HttpSession session, @RequestBody EventRequest eventRequest) {
        return EventResponse.creasteTestEventResponse();
    }

    @RequestMapping(path = "/events-for-host.json", method = RequestMethod.POST)
    public EventResponse eventsForHost(HttpSession session, @RequestBody HostEventRequest hostEventRequest) {
        return EventResponse.creasteTestEventResponse();
    }

    @RequestMapping(path = "/create-event.json", method = RequestMethod.POST)
    public EventResponse createEvent(HttpSession session, @RequestBody Event event) {
        return EventResponse.creasteTestEventResponse();
    }

    @RequestMapping(path = "/edit-event.json", method = RequestMethod.POST)
    public EventResponse editEvent(HttpSession session, @RequestBody Event event) {
        return EventResponse.creasteTestEventResponse();
    }

    @RequestMapping(path = "/delete-event.json", method = RequestMethod.POST)
    public EventResponse deleteEvent(HttpSession session, @RequestBody EventRequest eventRequest) {
        return EventResponse.creasteTestEventResponse();
    }

    @RequestMapping(path = "/leave-event.json", method = RequestMethod.POST)
    public EventResponse leaveEvent(HttpSession session, @RequestBody EventRequest eventRequest) {
        return EventResponse.creasteTestEventResponse();
    }

    @RequestMapping(path = "/edit-individual.json", method = RequestMethod.POST)
    public IndividualResponse editIndividual(HttpSession session, @RequestBody EditIndividualRequest editIndividualRequest) {
        return IndividualResponse.createTestIndividualResponse();
    }

    @RequestMapping(path = "/edit-organization.json", method = RequestMethod.POST)
    public OrganizationResponse editOrganization(HttpSession session, @RequestBody EditOrganizationRequest editOrganizationRequest) {
        return OrganizationResponse.createTestOrganizationResponse();
    }

}
