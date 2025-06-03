package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data

public class CandidateEducation {
    @Id
    @GeneratedValue
    private long id;
    private String institution;
    private String degree;
    private String year_of_passing;

    @OneToOne(cascade = jakarta.persistence.CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ToString.Exclude
    private Candidates candidate;




}
