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

    private Boolean sent;

    private LocalDate sent_at;

    private int retry_count;

    private String error_message;
    @OneToOne (cascade = jakarta.persistence.CascadeType.ALL)
    @jakarta.persistence.JoinColumn(name = "candidates_id", referencedColumnName = "id")
    private Candidates candidates;

}
