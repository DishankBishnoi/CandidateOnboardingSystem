package CanditateonboardingSystem.CanditateonboardingSystem.Repository;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidateBankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateBankInformationRepository extends JpaRepository<CandidateBankInfo,Long> {
}
