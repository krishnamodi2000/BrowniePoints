package com.GRP3.BPA.utils;

import com.GRP3.BPA.service.JwtService;
import com.GRP3.BPA.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class JWTAuthenticationUtil {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private JwtService jwtService;
    private static final int BEGIN_INDEX=7;

    public ResponseEntity<String> validateAuthorizationHeader(String authorizationHeader) {
        String token = authorizationHeader.substring(BEGIN_INDEX);
        String email = jwtService.extractUsername(token);
        String teacherId = teacherService.findTeacherAssociatedWithUser(email);
        if (!jwtService.isJWTTokenValid(token)) {
            return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(teacherId);
    }
}
