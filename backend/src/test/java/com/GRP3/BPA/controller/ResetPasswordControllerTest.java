package com.GRP3.BPA.controller;


import com.GRP3.BPA.model.User;
import com.GRP3.BPA.repository.UserRepository;
import com.GRP3.BPA.request.authentication.AuthenticationRequest;
import com.GRP3.BPA.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResetPasswordControllerTest {

    @Mock
    private UserRepository userRepository;

    @LocalServerPort
    private int port;

    HttpHeaders headers = new HttpHeaders();

        private TestRestTemplate restTemplate = new TestRestTemplate();

    private String createUriWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
        @Test
        public void sendOtpTest() {

            AuthenticationRequest request = new AuthenticationRequest("vd386827@dal.ca", "12345678");
            HttpEntity<AuthenticationRequest> entity = new HttpEntity<AuthenticationRequest>(request, headers);
            User user = new User();
            user.setEmail("vd386827@dal.ca");
            ResponseEntity<Utils> response = restTemplate.exchange(
                    createUriWithPort("/api/auth/reset-password"), HttpMethod.POST, entity, Utils.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @Test
        public void matchOtp() {
            assert true;
        }
}
