package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor


public class CandidateEducationDto {
    @Id @GeneratedValue
    private long id;
    private String institution;
    private String degree;
    private int year_of_passing;

    @OneToOne(cascade = CascadeType.ALL)
    @jakarta.persistence.JoinColumn(name = "candidates_id", referencedColumnName = "id")
    private Candidates candidates;

}
