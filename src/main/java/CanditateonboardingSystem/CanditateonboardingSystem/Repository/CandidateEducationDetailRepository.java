package CanditateonboardingSystem.CanditateonboardingSystem.Repository;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidateEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateEducationDetailRepository extends JpaRepository<CandidateEducation,Long> {
    CandidateEducation findByCandidateId(Long candidateId);
}
