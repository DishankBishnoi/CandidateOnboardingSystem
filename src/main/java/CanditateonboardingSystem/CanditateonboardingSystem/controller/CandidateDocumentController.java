package CanditateonboardingSystem.CanditateonboardingSystem.controller;


import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidateDocumentDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.MessageResponsesDto;
import CanditateonboardingSystem.CanditateonboardingSystem.service.CandidateDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/candidate/{CandidateId}/document")
public class CandidateDocumentController {
    @Autowired
    private CandidateDocumentService candidateDocumentService;


    @PostMapping("/upload")
    public ResponseEntity<CandidateDocumentDto> uploadDocument(
            @PathVariable long candidateId,
            @RequestParam String documentType,
            @RequestParam MultipartFile file) {

        candidateDocumentService.uploadDocument(candidateId, documentType, file);
        return ResponseEntity.ok(new CandidateDocumentDto(
                candidateId,
                file.getOriginalFilename(),
                documentType,
                false
        ));
    }

    @PutMapping("/{documentId}/verify")
    public ResponseEntity<CandidateDocumentDto> verifyDocument(@PathVariable long documentId , @PathVariable Long candidateId   ) {
        candidateDocumentService.verifyDocument(documentId,candidateId);
        return ResponseEntity.ok(new MessageResponsesDto("Verified Document Successfully"));
    }
}
