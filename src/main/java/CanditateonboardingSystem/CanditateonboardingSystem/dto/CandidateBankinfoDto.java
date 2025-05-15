package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateBankinfoDto {

    @Id
    @GeneratedValue
    private long id;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidates candidates;

}
