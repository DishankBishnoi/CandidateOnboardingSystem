package CanditateonboardingSystem.CanditateonboardingSystem.service;


import CanditateonboardingSystem.CanditateonboardingSystem.Exceptions.CandidateDocumentNotFoundExceptions;
import CanditateonboardingSystem.CanditateonboardingSystem.Exceptions.CandidateNotFound;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.CandidateDocumentDetailsRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.CandidateRecordsRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidateDocument;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class CandidateDocumentService {
    @Autowired
    private CandidateRecordsRepository CandidateRecordsRepository;

    @Autowired
    private CandidateDocumentDetailsRepository candidateDocumentDetailsRepository;


    public void uploadDocument(long candidateId, String documentType, MultipartFile file){
        Candidates candidate = CandidateRecordsRepository.findById(candidateId).orElseThrow(()->
                new CandidateNotFound(candidateId));

        try {
            CandidateDocument candidateDocument = new CandidateDocument();
            candidateDocument.setCandidate(candidate);
            candidateDocument.setDocumentType(documentType);
            candidateDocument.setFileData(file.getBytes());
            candidateDocument.setIsVerified(false);
            candidateDocumentDetailsRepository.save(candidateDocument);
        }catch (IOException e){
            throw new RuntimeException("Error uploading file: " + file.getOriginalFilename(), e);
        }
    }

    public void verifyDocument(Long documentId, Long candidateId){
        CandidateDocument candidateDocument = candidateDocumentDetailsRepository.findById(documentId).orElseThrow
                (()-> new CandidateDocumentNotFoundExceptions(candidateId,documentId));

        candidateDocument.setIsVerified(true);
        candidateDocumentDetailsRepository.save(candidateDocument);
    }


}
