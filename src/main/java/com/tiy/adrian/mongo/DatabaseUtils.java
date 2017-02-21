package com.tiy.adrian.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.tiy.adrian.model.Event;

/**
 * Created by dbash on 2/13/2017.
 */
public class DatabaseUtils {
    public static final String DB_CONNECTION_URL = "mongodb://localhost:27017";

    public static final DBObject toDBOjbect(Event event) {
        return new BasicDBObject("eventId", event.getId())
                .append("name", event.getName())
                .append("type", event.getType())
                .append("host", event.getHost())
                .append("hostId", event.getHostId())
                .append("photo", event.getPhoto())
                .append("location", event.getLocation())
                .append("address", event.getAddress())
                .append("city", event.getCity())
                .append("state", event.getState())
                .append("zip", event.getZip())
                .append("startTime", event.getStartTime())
                .append("endTime", event.getEndTime())
                .append("details", event.getDetails())
                .append("userIds", event.getUserIds());

//                .append("address", new BasicDBObject("street", person.getAddress().getStreet())
//                        .append("city", person.getAddress().getTown())
//                        .append("phone", person.getAddress().getPhone()))
    }
}
