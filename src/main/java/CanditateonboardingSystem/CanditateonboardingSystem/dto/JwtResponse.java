package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import CanditateonboardingSystem.CanditateonboardingSystem.enums.RoleName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String userName;
    private String email;
    private RoleName role;

    public JwtResponse(String token, Long id, String userName, String email, RoleName role) {
        this.token = token;
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }
}
