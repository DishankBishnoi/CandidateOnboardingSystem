package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import CanditateonboardingSystem.CanditateonboardingSystem.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
@Builder
public class Users {
    @Id
    @GeneratedValue private long id;
    private String username;
    private String password;
    private String email;

    private String otp;
    private LocalTime otpExpiryTime;

    @Enumerated(EnumType.STRING)
    private RoleName role;



}
