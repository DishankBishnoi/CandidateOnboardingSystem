package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}

