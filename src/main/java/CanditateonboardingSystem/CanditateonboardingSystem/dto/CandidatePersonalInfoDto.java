package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CandidatePersonalInfoDto {
    @Id @GeneratedValue
    private long id;
    private Date dob;
    private String gender;
    private String nationality;
    private String address;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "candidates_id", referencedColumnName = "id")
    private Candidates candidates;

}
