//package com.GRP3.BPA.config;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.junit.Assert.assertNotNull;
//import jakarta.servlet.Filter;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import java.util.List;
//
//
////
////@SpringBootTest
////public class SecurityConfigurationTest {
////
//////    @Test
//////    public void testSecurityFilterChain() throws Exception {
//////        JwtAuthenticationFilter jwtAuthFilter = mock(JwtAuthenticationFilter.class);
//////        AuthenticationProvider authenticationProvider = mock(AuthenticationProvider.class);
//////        SecurityConfiguration securityConfiguration = new SecurityConfiguration();
//////
//////        HttpSecurity http = mock(HttpSecurity.class);
//////        SecurityFilterChain securityFilterChain = securityConfiguration.securityFilterChain(http);
//////
//////        // Verify that the security filter chain is not null
//////        assertNotNull(securityFilterChain);
//////
//////        // Verify that the security filter chain contains the JwtAuthenticationFilter before the UsernamePasswordAuthenticationFilter
//////        assertThat(securityFilterChain.getFilters().get(0).doFilter().get(0)).isEqualTo(jwtAuthFilter);
//////        assertThat(securityFilterChain.getFilters().get(0).doFilter().get(1)).isEqualTo(UsernamePasswordAuthenticationFilter.class);
//////
////@Test
////public void testSecurityFilterChain() throws Exception {
////    JwtAuthenticationFilter jwtAuthFilter = mock(JwtAuthenticationFilter.class);
////    AuthenticationProvider authenticationProvider = mock(AuthenticationProvider.class);
////    SecurityConfiguration securityConfiguration = new SecurityConfiguration();
////
////    HttpSecurity http = mock(HttpSecurity.class);
////    SecurityFilterChain securityFilterChain = securityConfiguration.securityFilterChain(http);
////
////    // Verify that the security filter chain is not null
////    assertNotNull(securityFilterChain);
////
////    // Verify that the security filter chain contains the JwtAuthenticationFilter before the UsernamePasswordAuthenticationFilter
////    List<Filter> filters = securityFilterChain.getFilters();
////    assertThat(filters.size()).isGreaterThan(0);
////    assertThat(filters.get(0)).isEqualTo(jwtAuthFilter);
////    assertThat(filters.get(filters.size() - 1)).isEqualTo(UsernamePasswordAuthenticationFilter.class);
////}
////
////    private <SELF extends AbstractByteAssert<SELF>> AbstractByteAssert assertThat(int size) {
////    }
////}
//
//@RunWith(MockitoJUnitRunner.class)
//public class SecurityConfigurationTest {
//
//    @Mock
//    private JwtAuthenticationFilter jwtAuthFilter;
//
//    @Mock
//    private AuthenticationProvider authenticationProvider;
//
//    @Mock
//    private HttpSecurity http;
//
//    @InjectMocks
//    private SecurityConfiguration securityConfiguration;
//
//    @Test
//    @Test
//    public void testSecurityFilterChain() throws Exception {
//        // Arrange
//        SecurityFilterChain securityFilterChain = securityConfiguration.securityFilterChain(http);
//
//        // Act
//        List<Filter> filters = securityFilterChain.getFilterChains().get(0).getFilters();
//
//        // Assert
//        assertThat(securityFilterChain).isNotNull();
//        assertThat(securityFilterChain.getFilterChains()).hasSize(1);
//        assertThat(filters).hasSizeGreaterThan(0);
//        assertThat(filters.get(0)).as("First filter should be JwtAuthenticationFilter").isEqualTo(jwtAuthFilter);
//        assertThat(filters.get(filters.size() - 1)).as("Last filter should be UsernamePasswordAuthenticationFilter").isEqualTo(UsernamePasswordAuthenticationFilter.class);
//    }
//
//
//}
//
