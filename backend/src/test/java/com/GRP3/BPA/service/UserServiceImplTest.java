//package com.GRP3.BPA.service;
//
//import com.GRP3.BPA.model.User;
//import com.GRP3.BPA.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;;
//import org.mockito.junit.MockitoJUnitRunner;
//
//@RunWith(MockitoJUnitRunner.class)
//public class UserServiceImplTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    @Test
//    public void testUpdateOTP() {
//        // create a mock user object
//        User user = new User();
//        user.setEmail("testuser@example.com");
//
//        // call the updateOTP method
//        userService.updateOTP(user);
//
//        // verify that the user object has a non-null OTP
//        assertNotNull(user.getOtp());
//
//        // verify that the userRepository's save method was called with the correct argument
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    public void testUpdateOTPWithNullUser() {
//        // call the updateOTP method with a null user object
//        User user = null;
//        User result = userService.updateOTP(user);
//
//        // verify that the result is null
//        assertNull(result);
//
//        // verify that the userRepository's save method was not called
//        verify(userRepository, times(0)).save(any(User.class));
//    }
//}
//
//
//
//
//
