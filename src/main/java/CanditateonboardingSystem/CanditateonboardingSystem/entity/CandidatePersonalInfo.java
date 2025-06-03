package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePersonalInfo {
    @Id
    @GeneratedValue
    private long  id;
    private Date dob;
    private String gender;
    private String address;
    private String city;
    private String nationality;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    @ToString.Exclude
    private Candidates candidate;

}
