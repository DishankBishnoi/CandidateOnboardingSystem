package CanditateonboardingSystem.CanditateonboardingSystem.Repository;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidateDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDocumentDetailsRepository extends JpaRepository<CandidateDocument, Long> {
    CandidateDocument findByCandidateId(Long candidateId);
}
