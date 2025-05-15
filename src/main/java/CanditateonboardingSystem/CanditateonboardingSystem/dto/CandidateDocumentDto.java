package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDocumentDto {
    private long candidateId;
    private String fileName;
    private String documentType;
    private Boolean isVerified;
}
