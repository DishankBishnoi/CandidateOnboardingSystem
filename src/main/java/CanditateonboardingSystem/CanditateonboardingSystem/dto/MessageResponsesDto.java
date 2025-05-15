package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponsesDto {
    private String message;

    public MessageResponsesDto(String message) {

        this.message = message;
    }

}
