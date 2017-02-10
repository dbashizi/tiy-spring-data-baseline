package com.tiy.conor.model;

import java.util.Date;

/**
 * Created by dbash on 2/8/2017.
 */
public class Team {
    private long id;
    private long dk_id;
    private String city;
    private String team_name;
    private Date created_at;
    private Date updated_at;
    private String abbreviation;

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Team Name: " + city + " " + team_name + "\n");
        sb.append("Team ID: " + id + "\n");
        sb.append("Team Abbreviation: " + abbreviation+ "\n");

        return sb.toString();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDk_id() {
        return dk_id;
    }

    public void setDk_id(long dk_id) {
        this.dk_id = dk_id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
