package com.tiy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tiy.conor.model.Team;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dbash on 2/8/2017.
 */
public class HttpConnectiontTests {

    @Test
    public void testDateConversion() {
        Date testDate = Date.from(Instant.ofEpochMilli(1488049252000L));
        Date secondTestDate = Date.from(Instant.ofEpochMilli(1488056402000L));
        System.out.println(testDate.toString());
        System.out.println(secondTestDate.toString());
    }

    @Test
    public void testSendTextMessages() {
        final String ACCOUNT_SID = "AC3bbbd5407d2c65824221b5f5e41705a1";
        final String AUTH_TOKEN = "d99711ac46014412ef31c1e3a9368316";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+16095014251"),
                new PhoneNumber("+16786078640"),
                "Testing Twilio API with Java").create();

        System.out.println(message.getSid());
    }

    @Test
    public void testSimpleHTTPCall() {
        CloseableHttpClient httpClient = null;
        ObjectMapper mapper = new ObjectMapper();

        // different ways to use Apache Http Utils: http://www.baeldung.com/httpclient-post-http-request

        try {
            httpClient = HttpClients.createDefault();
            String apiKey = "t5sJ0Lyfh7VYOjr39FPanZSdHeqAxQiT";
            HttpPost httpPostRequest = new HttpPost("http://api.probasketballapi.com/team");

            // code to attach a JSON object to the POST request
            if (false) {
                String hardcodedJSON = "{'id':1,'name':'John'}";
                String serializedJSON = mapper.writeValueAsString(new Team());
                StringEntity entity = new StringEntity(hardcodedJSON);
                httpPostRequest.setEntity(entity);
                httpPostRequest.setHeader("Accept", "application/json");
                httpPostRequest.setHeader("Content-type", "application/json");
            } else {
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("api_key", apiKey));
                httpPostRequest.setEntity(new UrlEncodedFormEntity(params));
            }

            HttpResponse httpResponse = httpClient.execute(httpPostRequest);

            assertEquals(200, httpResponse.getStatusLine().getStatusCode());

            System.out.println("----------------------------------------");
            System.out.println(httpResponse.getStatusLine());
            System.out.println("----------------------------------------");

            HttpEntity entity = httpResponse.getEntity();

            assertNotNull(entity);

            BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
            String currentLine;
            StringBuffer jsonStringBuffer = new StringBuffer();
            while ((currentLine = reader.readLine()) != null) {
                jsonStringBuffer.append(currentLine);
            }
            reader.close();

            System.out.println(jsonStringBuffer.toString());

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mapper.setConfig(mapper.getDeserializationConfig().with(df));
            TypeReference teamListType = new TypeReference<List<Team>>() {};
            List<Team> teams = mapper.readValue(jsonStringBuffer.toString(), teamListType);

            System.out.println("Got " + teams.size() + " teams back");
            assertEquals(30, teams.size());

            for (Team team : teams) {
                System.out.println(team);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try { httpClient.close(); } catch (IOException ioe) { ioe.printStackTrace(); }
            }
            // TODO add reader.close() here
        }
    }
}
