package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class CandidateDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String document_type;
    private String file_url;
    private Boolean is_verified;
    @OneToOne (cascade = jakarta.persistence.CascadeType.ALL)
    @jakarta.persistence.JoinColumn(name = "candidates_id", referencedColumnName = "id")
    private Candidates candidates;
}
