package com.GRP3.BPA.service;

import com.GRP3.BPA.request.authentication.AuthenticationRequest;
import com.GRP3.BPA.response.authentication.AuthenticationResponse;
import com.GRP3.BPA.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
/**
 * This class provides authentication service to users.
 */
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    /**
     * This method authenticates the user using the provided authentication request.
     * @param request the authentication request object containing the user's email and password.
     * @return an authentication response object containing the generated JWT token.
     * @throws RuntimeException if the provided credentials are invalid.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws RuntimeException {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        }
        catch (InternalAuthenticationServiceException e){
            throw new RuntimeException("Invalid Credentials");
        }
        System.out.println("testing");
        var user = repository.findByEmail(request.getEmail());
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
