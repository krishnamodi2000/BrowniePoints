package com.GRP3.BPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name="User", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    /**
     * The ID of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;

    /**
     * The user ID associated with the user.
     */
    @Getter @Setter
    private String userId;

    /**
     * The email address associated with the user.
     */
    @Getter @Setter
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The first name of the user.
     */
    @Getter @Setter
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the user.
     */
    @Getter @Setter
    @Column(name = "last_name")
    private String lastName;


    /**
     * The password associated with the user.
     */
    @Getter @Setter
    @Column
    private String password;

    /**
     * The role of the user.
     */
    @Getter @Setter
    @Column
    private String role;


    /**
     * The one-time password associated with the user.
     */
    @Getter @Setter
    @Column(name = "otp")
    private String otp;

    /**
     * The authentication status of the user.
     */
    @Getter @Setter
    @Column(name = "is_authenticated")
    private Boolean isAuthenticated;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public String getPassword() {
        return password;
    }


    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isPresent() {
        return true;
    }

    public String getToken() {
        return null;
    }
}
