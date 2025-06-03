package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import CanditateonboardingSystem.CanditateonboardingSystem.enums.OnboardingStatus;
import CanditateonboardingSystem.CanditateonboardingSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name="candidates")
public class Candidates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

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

    @ManyToOne(cascade = CascadeType.ALL)
    private CandidateDocument candidateDocument;


    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
        if (status == null) {
            status = Status.APPLIED;
        }
        if (OnboardingStatus == null) {
            OnboardingStatus = OnboardingStatus.NOT_STARTED;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



}
