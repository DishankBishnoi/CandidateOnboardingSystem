package CanditateonboardingSystem.CanditateonboardingSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Entity
@Table(name = "job_offer_notification")
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class JobOfferNotification {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidates candidates;

    private Boolean sent;

    @Column(name = "sent_at")
    private LocalDate sent_at;

    @Column(name = "retry_count")
    private int retry_count;

    @Column(name = "error_message")
    private String error_message;



}
