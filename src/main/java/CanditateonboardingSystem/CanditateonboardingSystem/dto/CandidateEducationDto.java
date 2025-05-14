package CanditateonboardingSystem.CanditateonboardingSystem.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CandidateEducationDto {
    @NotEmpty
    private String institution;
    @NotEmpty
    private String degree;
    @NotEmpty
    private String year_of_passing;

}
