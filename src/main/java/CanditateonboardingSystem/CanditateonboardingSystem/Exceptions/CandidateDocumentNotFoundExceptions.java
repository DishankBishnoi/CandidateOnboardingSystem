package CanditateonboardingSystem.CanditateonboardingSystem.Exceptions;

public class CandidateDocumentNotFoundExceptions extends RuntimeException {
    public CandidateDocumentNotFoundExceptions(long candidateId,long documentId) {
        super("Candidate Document not found for id : " + documentId + "does not exist");
    }
}
