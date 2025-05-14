package CanditateonboardingSystem.CanditateonboardingSystem.Repository;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidatePersonalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatePersonalInfoRespository extends JpaRepository< CandidatePersonalInfo,Long> {
    CandidatePersonalInfo findByCandidateId(Long candidateId);

}
