package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.*;

public class CandidateBankInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bank_name;
    private String account_number;
    private String ifsc_code;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "candidates_id", referencedColumnName = "id")
    private Candidates candidates;
}
