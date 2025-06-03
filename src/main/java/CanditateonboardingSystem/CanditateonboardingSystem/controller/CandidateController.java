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
    CandidateService candidateService;


// 7 Get Commands

//   1) Get means fetching or viewing all the records
    @GetMapping
    public String getAll(){return "All Candidates";}


//   2)  This method is used to get the count of the records of the candidates from the database
    @GetMapping("/count")
    public ResponseEntity<?> getCandidateCount(){
        int countfinal = candidateService.getCandidateCount();
        return ResponseEntity.ok(new MessageResponsesDto("Total candidate is"+(countfinal)));

    }
//  3) This method is used to get the details of the candidate from the database
    @GetMapping("/{candidateId}")
    public ResponseEntity<?> getCandidateById(@PathVariable long candidateId) {
        Candidates candidates = candidateService.getCandidateById(candidateId);
        return ResponseEntity.ok(new CandidatesDto(
                candidates.getFirstName(),
                candidates.getLastName(),
                candidates.getPhoneNumber(),
                candidates.getEmail()
        ));
    }
//  4) This method is used to get the personal information of the candidate from the database
    @GetMapping("/{candidateId}/personal-info")
    public ResponseEntity<?> getPersonalInfo(@PathVariable long candidateId){
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
    public ResponseEntity<?> getEducation(@PathVariable long candidateId){
    CandidateEducation candidateEducation = candidateService.getCandidateEducation(candidateId);
    return ResponseEntity.ok(new CandidateEducationDto(
            candidateEducation.getInstitution(),
            candidateEducation.getYear_of_passing(),
            candidateEducation.getDegree()

    ));
    }
//  6) This method is used to get the status of the candidate from the database : For ex: Applied, Onboarding, Interview
    @GetMapping("/{candidateId}/status")
    public ResponseEntity<?> getCandidatesStatus(@PathVariable long candidateId){
        Status status = candidateService.getCandidatesStatus(candidateId);
        return ResponseEntity.ok(new MessageResponsesDto("Status: " + status));
    }
//  7) This method is used to get the onboarding status of the candidate from the database
    @GetMapping("/{candidateId}/onboard-status")
    public ResponseEntity<?> getCandidatesOnboardStatus(@PathVariable long candidateId) {
        OnboardingStatus status = candidateService.getCandidatesOnboardStatus(candidateId);
        return ResponseEntity.ok(new MessageResponsesDto("Status: " + status));
    }


//    Post Commands

    //    Post means creating a new record
    @PostMapping
    public ResponseEntity<?> saveCandidate(@RequestBody @Validated CandidatesDto candidatesDto){


        candidateService.addNewCandidate(candidatesDto);
        return ResponseEntity.ok(new MessageResponsesDto("Candidate added successfully!"));
    }

    @PostMapping("/{candidateId}/personal-info")
    public ResponseEntity<?> addPersonalInfo(@RequestBody @Validated  CandidatePersonalInfoDto candidatePersonalInfoDto, @PathVariable long candidateId){
        candidateService.addPersonalInfo(candidatePersonalInfoDto ,candidateId);
        return ResponseEntity.ok(new MessageResponsesDto("Personal info added successfully"));
    }

    @PostMapping("{candidateId}/education-info")
    public ResponseEntity<?> addCandidateEducation(@RequestBody @Validated CandidateEducationDto candidateEducationDto, @PathVariable long candidateId){
        candidateService.saveEducation(candidateEducationDto,candidateId);
        return ResponseEntity.ok(new MessageResponsesDto("Education info added successfully"));
    }

    @PutMapping("{id}/status")
    public ResponseEntity<?> updateCandidateStatus(@RequestBody Status status, @PathVariable long id){
        candidateService.updateCandidateStatus(id,status);
        return ResponseEntity.ok(new MessageResponsesDto("Candidate status updated successfully"));
    }

    @PutMapping("{id}/onboard-status")
    public ResponseEntity<?> updateCandidateOnboardStatus(@RequestBody OnboardingStatus onboardingStatus, @PathVariable long id){
        candidateService.updateCandidateOnboardStatus(id,onboardingStatus);
        return ResponseEntity.ok(new MessageResponsesDto("Candidate onboarding status updated successfully"));
    }


}


