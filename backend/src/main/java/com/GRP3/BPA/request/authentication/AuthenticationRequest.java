package com.GRP3.BPA.request.authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    /**
     * The email of the user trying to authenticate.
     */
    private String email;


    /**
     * The password of the user trying to authenticate.
     */
    String password;
}