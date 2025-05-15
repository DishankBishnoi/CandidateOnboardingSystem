package CanditateonboardingSystem.CanditateonboardingSystem.Repository;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.JobOfferNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDetailsRepository extends JpaRepository<JobOfferNotification, Long> {
}
