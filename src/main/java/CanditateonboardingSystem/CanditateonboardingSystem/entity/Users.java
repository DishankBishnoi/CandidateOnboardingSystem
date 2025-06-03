package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import CanditateonboardingSystem.CanditateonboardingSystem.enums.RoleName;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    private long id;

    private String username;
    private String password;
    private String email;

    private String otp;
    private LocalTime otpExpiryTime;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    // Optional fields for real account management
    // private boolean accountNonExpired = true;
    // private boolean accountNonLocked = true;
    // private boolean credentialsNonExpired = true;
    // private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // you can populate this with actual roles later
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // or use: return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // or use: return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // or use: return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return true; // or use: return enabled;
    }
}
