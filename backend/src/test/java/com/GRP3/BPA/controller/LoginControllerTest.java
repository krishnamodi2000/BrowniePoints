package com.GRP3.BPA.controller;

import com.GRP3.BPA.request.authentication.AuthenticationRequest;
import com.GRP3.BPA.response.authentication.AuthenticationResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoginControllerTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    HttpHeaders headers = new HttpHeaders();
    @Test
    public void authenticate() {
        AuthenticationRequest request = new AuthenticationRequest("vd386827@dal.ca", "12345678");
        HttpEntity<AuthenticationRequest> entity = new HttpEntity<AuthenticationRequest>(request, headers);
        ResponseEntity<AuthenticationResponse> response = restTemplate.exchange(
                createUriWithPort("/api/auth/login"), HttpMethod.POST, entity, AuthenticationResponse.class);

        AuthenticationResponse actual = new AuthenticationResponse();
        assertEquals(response.getBody().getClass().getName(), actual.getClass().getName());
    }

    private String createUriWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void status(){
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                createUriWithPort("/status"), HttpMethod.GET, entity, String.class);
        String data = "Application is running.";
        assertEquals(data, response.getBody());
    }
}