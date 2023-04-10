package utils;

import com.GRP3.BPA.service.JwtService;
import com.GRP3.BPA.service.TeacherService;
import com.GRP3.BPA.utils.JWTAuthenticationUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class JWTAuthenticationUtilTest {

    @Mock
    JwtService jwtService;
    @Mock
    TeacherService teacherService;
    @InjectMocks
    JWTAuthenticationUtil jwtAuthenticationUtil;
    @Test
    public void testValidateAuthorizationHeader_Valid() {
        String authorizationHeader = "Bearer valid_token";
        Mockito.when(jwtService.extractUsername(Mockito.anyString())).thenReturn("valid_email");
        Mockito.when(teacherService.findTeacherAssociatedWithUser(Mockito.anyString())).thenReturn("valid_teacherId");
        Mockito.when(jwtService.isJWTTokenValid(Mockito.anyString())).thenReturn(true);
        ResponseEntity<String> responseEntity = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("valid_teacherId", responseEntity.getBody());
    }

    @Test
    public void testValidateAuthorizationHeader_InvalidToken() {
        String authorizationHeader = "Bearer invalid_token";
        Mockito.when(jwtService.isJWTTokenValid(Mockito.anyString())).thenReturn(false);
        ResponseEntity<String> responseEntity = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid Token", responseEntity.getBody());
    }

    @Test
    public void testValidateAuthorizationHeader_InvalidEmail() {
        String authorizationHeader = "Bearer valid_token";
        Mockito.when(jwtService.extractUsername(Mockito.anyString())).thenReturn(null);
        ResponseEntity<String> responseEntity = jwtAuthenticationUtil.validateAuthorizationHeader(authorizationHeader);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }


}
