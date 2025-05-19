package CanditateonboardingSystem.CanditateonboardingSystem.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateBankinfoDto {
    @NotEmpty
    private String bankName;
    @NotEmpty
    private String accountNumber;
    @NotEmpty
    private String ifscCode;


}
