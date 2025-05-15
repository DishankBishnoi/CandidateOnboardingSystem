package CanditateonboardingSystem.CanditateonboardingSystem.controller;

import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidateEducationDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidatePersonalInfoDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidatesDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.MessageResponsesDto;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidateEducation;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidatePersonalInfo;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import CanditateonboardingSystem.CanditateonboardingSystem.enums.OnboardingStatus;
import CanditateonboardingSystem.CanditateonboardingSystem.enums.Status;
import CanditateonboardingSystem.CanditateonboardingSystem.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {
//   We are using @Autowired to inject the service into the service class
//    Flow should be like this Controller>Service>Dto(Data transfer Object)>Repository
    @Autowired
    private CandidateService candidateService;


// 7 Get Commands

//   1) Get means fetching or viewing all the records
    @GetMapping
    public String getAll(){return "All Candidates";}


//   2)  This method is used to get the count of the records of the candidates from the database
    @GetMapping("/count")
    public ResponseEntity<?> getCandidateCount(){
        int countfinal = candidateService.getCandidateCount();;
        return ResponseEntity.ok(new MessageResponsesDto("Total candidate i5s"+(countfinal)));

    }
//  3) This method is used to get the details of the candidate from the database
    @GetMapping("/{candidateId}")
    public ResponseEntity<?> getCandidateById(@PathVariable Long candidateId) {
        Candidates candidates = candidateService.getCandidateById(candidateId);
        return ResponseEntity.ok(new CandidatesDto(
                candidates.getFirstname(),
                candidates.getLastname(),
                candidates.getPhone(),
                candidates.getEmail()
        ));
    }
//  4) This method is used to get the personal information of the candidate from the database
    @GetMapping("/{candidateId}/personal-info")
    public ResponseEntity<?> getPersonalInfo(@PathVariable Long candidateId){
        CandidatePersonalInfo candidatePersonalInfo = candidateService.getCandidatePersonalInfo(candidateId);
        return ResponseEntity.ok(new CandidatePersonalInfoDto(
                candidatePersonalInfo.getDob(),
                candidatePersonalInfo.getAddress(),
                candidatePersonalInfo.getGender(),
                candidatePersonalInfo.getNationality()
                ));

    }
//  5) This method is used to get the education information of the candidate from the database
    @GetMapping("/{candidateId}/education-info")
    public ResponseEntity<?> getEducation(@PathVariable Long candidateId){
    CandidateEducation candidateEducation = candidateService.getCandidateEducation(candidateId);
    return ResponseEntity.ok(new CandidateEducationDto(
            candidateEducation.getInstitution(),
            candidateEducation.getYear_of_passing(),
            candidateEducation.getDegree()

    ));
    }
//  6) This method is used to get the status of the candidate from the database : For ex: Applied, Onboarding, Interview
    @GetMapping("/{candidateId}/status")
    public ResponseEntity<?> getCandidatesOnboardStatus(@PathVariable long id){
        OnboardingStatus status = candidateService.getCandidateOnboardingStatus(id);
        return ResponseEntity.ok(new MessageResponsesDto("Status: " + status));
    }
//  7) This method is used to get the onboarding status of the candidate from the database
    @GetMapping("/{candidateId}/onboarding-status")
    public ResponseEntity<?> getCandidatesOnboardStatus(@PathVariable long id, @RequestParam String status) {
        OnboardingStatus onboardingStatus = candidateService.getCandidateOnboardingStatus(id);
        return ResponseEntity.ok(new MessageResponsesDto("Status: " + onboardingStatus));
    }


//    Post Commands

    //    Post means creating a new record
    @PostMapping
    public ResponseEntity<?> saveCandidate(@RequestBody @Validated CandidatesDto CandidateDto){
        candidateService.addNewCandidate(CandidateDto);
        return ResponseEntity.ok(new MessageResponsesDto("Candidate added successfully!"));
    }

    @PostMapping("/{candidateId}/personal-info")
    public ResponseEntity<?> addPersonalInfo(@RequestBody @Validated  CandidatePersonalInfoDto candidatePersonalInfoDto, @PathVariable long candidateId){
        candidateService.addPersonalInfo(candidatePersonalInfoDto ,candidateId);
        return ResponseEntity.ok(new MessageResponsesDto("Personal info added successfully"));
    }

    @PostMapping("{candidateId}/education-info")
    public ResponseEntity<?> saveEducation(@RequestBody @Validated CandidateEducationDto candidateEducationDto, @PathVariable long candidateId){
        candidateService.saveEducation(candidateEducationDto,candidateId);
        return ResponseEntity.ok(new MessageResponsesDto("Education info added successfully"));
    }

    @PutMapping("{id}/status")
    public ResponseEntity<?> updateCandidateStatus(@RequestBody Status status, @PathVariable long id){
        candidateService.updateCandidateStatus(id,status);
        return ResponseEntity.ok(new MessageResponsesDto("Candidate status updated successfully"));
    }

    @PutMapping("{id}/status-onboarding")
    public ResponseEntity<?> updateCandidateOnboardingStatus(@RequestBody OnboardingStatus onboardingStatus, @PathVariable long id){
        candidateService.updateCandidateOnboardingStatus(id,onboardingStatus);
        return ResponseEntity.ok(new MessageResponsesDto("Candidate onboarding status updated successfully"));
    }


}


