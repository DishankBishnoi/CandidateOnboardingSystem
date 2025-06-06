package CanditateonboardingSystem.CanditateonboardingSystem.controller;


import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidateDocumentDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.MessageResponsesDto;
import CanditateonboardingSystem.CanditateonboardingSystem.service.CandidateDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/candidate/{candidateId}/document")
@ComponentScan
@Service
public class CandidateDocumentController {
    @Autowired
    private CandidateDocumentService candidateDocumentService;

//  This function is used to upload the document.
    @PostMapping("/upload")
    public ResponseEntity<CandidateDocumentDto>uploadDocument(
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
//    This function is used to verify the document.
    @PutMapping("/{documentId}/verify")
    public ResponseEntity<MessageResponsesDto> verifyDocument(@PathVariable Long documentId , @PathVariable Long candidateId) {
        candidateDocumentService.verifyDocument(candidateId, documentId);
        return ResponseEntity.ok(new MessageResponsesDto("Verified Document Successfully"));
    }



}
