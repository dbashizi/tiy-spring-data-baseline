package com.tiy.adrian.controllers;

import com.tiy.adrian.model.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

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

}
