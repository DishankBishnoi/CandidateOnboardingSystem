package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateBankInfo {
    @Id
    @GeneratedValue
    private long id;
    private String bank_name;
    private String account_number;
    private String ifsc_code;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidates candidate;
}
