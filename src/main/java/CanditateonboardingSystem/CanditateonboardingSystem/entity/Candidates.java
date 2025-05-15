package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import CanditateonboardingSystem.CanditateonboardingSystem.enums.OnboardingStatus;
import CanditateonboardingSystem.CanditateonboardingSystem.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private OnboardingStatus OnboardingStatus;


    @OneToOne(mappedBy = "candidate")
    private CandidatePersonalInfo candidatePersonalInfo;

    @OneToOne(mappedBy = "candidate")
    private CandidateBankInfo candidateBankInfo;

    @OneToOne(mappedBy = "candidate")
    private CandidateEducation candidateEducation;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    private CandidateDocument candidateDocument;

    private String firstname;
    private String Lastname;
    private String email;
    private String phone;
    private LocalDate created_at;
    private LocalDate updated_at;






}
