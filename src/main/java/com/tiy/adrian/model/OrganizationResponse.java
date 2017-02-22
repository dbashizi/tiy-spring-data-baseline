package com.tiy.adrian.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/10/2017.
 */
public class OrganizationResponse {
    private Organization organization;
    private List<Event> events = new ArrayList<Event>();
    private ErrorInformation errorInformation;

    public OrganizationResponse() {
    }

    public Organization getOrganization() {
        return organization;
    }

    public static OrganizationResponse createErrorOrganizationResponse(String errorMessage, int errorNumber) {
        OrganizationResponse response = new OrganizationResponse();
        response.setErrorInformation(new ErrorInformation(errorMessage, errorNumber));
        return response;
    }

    public static OrganizationResponse createOrganizationResponse(Organization organization, List<Event> events) {
        OrganizationResponse response = new OrganizationResponse();
        response.setOrganization(organization);
        response.setEvents(events);

        return response;
    }

    public static OrganizationResponse createTestOrganizationResponse() {
        OrganizationResponse organizationResponse = new OrganizationResponse();
        organizationResponse.setEvents(Event.createTestEvents());
        organizationResponse.setOrganization(Organization.createTestOrganization());
        return organizationResponse;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public ErrorInformation getErrorInformation() {
        return errorInformation;
    }

    public void setErrorInformation(ErrorInformation errorInformation) {
        this.errorInformation = errorInformation;
    }
}
