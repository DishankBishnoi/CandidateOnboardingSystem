package CanditateonboardingSystem.CanditateonboardingSystem.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDto {
    private Boolean sent;

    private LocalDateTime sentAt;

    private Integer retryCount;

    private String errorMessage;

}
