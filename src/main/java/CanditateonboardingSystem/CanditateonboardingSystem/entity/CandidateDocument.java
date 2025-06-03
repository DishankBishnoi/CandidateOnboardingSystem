package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDocument {
    @Id @GeneratedValue
    private long id;
    private String documentType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;



    private Boolean isVerified;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ToString.Exclude
    private Candidates candidate;
}
