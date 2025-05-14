package CanditateonboardingSystem.CanditateonboardingSystem.Repository;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateRecordsRepository extends JpaRepository<Candidates,Long> {

}
