package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponses {
    private String message;

    public MessageResponses(String message) {
        this.message = message;
    }

}
