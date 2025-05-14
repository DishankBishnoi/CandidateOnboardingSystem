package CanditateonboardingSystem.CanditateonboardingSystem.controller;

import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidatesDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.MessageResponses;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import CanditateonboardingSystem.CanditateonboardingSystem.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
    @Autowired
    private CandidateService candidateService;

    @PostMapping
    public ResponseEntity<?> saveCandidate(@RequestBody @Validated CandidatesDto CandidateDto){
        candidateService.addNewCandidate(CandidateDto);
        return ResponseEntity.ok(new MessageResponses("Candidate added successfully!"));
    }

    @PostMapping("/{candidateId}/personal-info")
    public ResponseEntity<?> updatePersonalInfo(@RequestBody @Validated Candidates candidate){
        candidateService.saveCandidate(candidate);
        return ResponseEntity.ok(new MessageResponses("Personal info updated successfully"));
    }

    @GetMapping
    public String getAll(){return "All Candidates";}

    @GetMapping("/count")
    public ResponseEntity<?> getCandidateCount(){
        int countfinal = CandidateService.getCandidateCount();
        return ResponseEntity.ok(new MessageResponses("Total candidate is"+(countfinal)));

    }
}

e