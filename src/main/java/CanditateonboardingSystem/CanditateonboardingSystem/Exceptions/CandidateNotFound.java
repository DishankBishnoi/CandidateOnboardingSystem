package CanditateonboardingSystem.CanditateonboardingSystem.Exceptions;

public class CandidateNotFound extends RuntimeException {
    public CandidateNotFound(Long id) {
        super("Candidate not found for id : " + id + "does not exist ");
    }
}
