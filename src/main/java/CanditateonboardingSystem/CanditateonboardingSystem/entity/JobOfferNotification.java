package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

public class JobOfferNotification {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private Boolean sent;
    private LocalDate sent_at;
    private int retry_count;
    private String error_message;
    @OneToOne (cascade = jakarta.persistence.CascadeType.ALL)
    @jakarta.persistence.JoinColumn(name = "candidates_id", referencedColumnName = "id")
    private Candidates candidates;

}
