package CanditateonboardingSystem.CanditateonboardingSystem.service;

import CanditateonboardingSystem.CanditateonboardingSystem.Exceptions.CandidateNotFound;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.CandidateBankInformationRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.CandidateEducationDetailRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.CandidatePersonalInfoRespository;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.CandidateRecordsRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidateEducationDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidatePersonalInfoDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.CandidatesDto;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidateEducation;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.CandidatePersonalInfo;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;

import CanditateonboardingSystem.CanditateonboardingSystem.enums.OnboardingStatus;
import CanditateonboardingSystem.CanditateonboardingSystem.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CandidateService {

//  We are initializing to inject the repositories into the service class
    CandidateRecordsRepository candidateRecordsRepository;
    CandidateBankInformationRepository candidateBankInformationRepository;
    CandidateEducationDetailRepository candidateEducationDetailRepository;
    CandidatePersonalInfoRespository candidatePersonalInfoRespository;

//  Using the constructor injection to inject the repositories into the service class
    @Autowired
    public CandidateService(CandidateRecordsRepository candidateRecordsRepository, CandidateBankInformationRepository candidateBankInformationRepository,
                            CandidateEducationDetailRepository candidateEducationDetailRepository,
                            CandidatePersonalInfoRespository candidatePersonalInfoRespository) {
        this.candidateRecordsRepository = candidateRecordsRepository;
        this.candidateBankInformationRepository = candidateBankInformationRepository;
        this.candidateEducationDetailRepository = candidateEducationDetailRepository;
        this.candidatePersonalInfoRespository = candidatePersonalInfoRespository;
    }

//  Method to get the candidate details from the database
    public void addNewCandidate(CandidatesDto candidatesDto){
        Candidates candidate= new Candidates();
        candidate.setFirstName(candidatesDto.getFirstname());
        candidate.setLastName(candidatesDto.getLastname());
        candidate.setPhoneNumber(candidatesDto.getPhone());
        candidate.setEmail(candidatesDto.getEmail());
        candidateRecordsRepository.save(candidate);
    }


//    Method to get the candidates personal information from the database

    public void addPersonalInfo(CandidatePersonalInfoDto candidatePersonalInfoDto , Long candidateId){
        CandidatePersonalInfo candidatePersonalInfo= new CandidatePersonalInfo();
        candidatePersonalInfo.setDob(candidatePersonalInfoDto.getDob());
        candidatePersonalInfo.setGender(candidatePersonalInfoDto.getGender());
        candidatePersonalInfo.setNationality(candidatePersonalInfoDto.getNationality());
        candidatePersonalInfo.setAddress(candidatePersonalInfoDto.getAddress());
        candidatePersonalInfo.setCandidate(candidateRecordsRepository.findById(candidateId).orElseThrow(()-> new CandidateNotFound(candidateId)));
        candidatePersonalInfoRespository.save(candidatePersonalInfo);
    }

//    Method to get the candidate education information from the database
    public void saveEducation(CandidateEducationDto candidateEducationDto,Long candidateId){
        CandidateEducation candidateEducation = new CandidateEducation();
        candidateEducation.setDegree(candidateEducationDto.getDegree());
        candidateEducation.setInstitution(candidateEducationDto.getInstitution());
        candidateEducation.setYear_of_passing(candidateEducationDto.getYearofpassing());
        candidateEducation.setCandidate(candidateRecordsRepository.findById(candidateId).orElseThrow(()-> new CandidateNotFound(candidateId)));
        candidateEducationDetailRepository.save(candidateEducation);

    }

//    Method to get the count of the candidates from the database
    public  int getCandidateCount(){
        int count =0;
        for(Candidates Candidates : candidateRecordsRepository.findAll()){
            count++;
        }
        return count;
    }

//    Method to get the candidate details from the database
    public Candidates getCandidateById(Long candidateId) {
        return candidateRecordsRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFound(candidateId));
    }


    //
    public CandidatePersonalInfo getCandidatePersonalInfo(Long candidateId){
        try {
             return candidatePersonalInfoRespository.findByCandidateId(candidateId);
         }catch (CandidateNotFound e){
             throw new CandidateNotFound(candidateId);
         }
    }

//
    public CandidateEducation getCandidateEducation(Long candidateId){
        try {
            return candidateEducationDetailRepository.findByCandidateId(candidateId);
        }catch (CandidateNotFound e){
            throw new CandidateNotFound(candidateId);
        }
    }

    public Status getCandidatesStatus(Long candidateId){
        Candidates candidate = candidateRecordsRepository.findById(candidateId).orElseThrow(()-> new CandidateNotFound(candidateId));
        return candidate.getStatus();
    }

    public OnboardingStatus getCandidatesOnboardStatus(Long id) {
        Candidates candidate = candidateRecordsRepository.findById(id).orElseThrow(() -> new CandidateNotFound(id));
        return candidate.getOnboardingStatus();
    }



    public void updateCandidateStatus(Long candidateId, Status status){
        Candidates candidate = candidateRecordsRepository.findById(candidateId).orElseThrow(()-> new CandidateNotFound(candidateId));
        candidate.setStatus(status);
        candidateRecordsRepository.save(candidate);
    }

    public void updateCandidateOnboardStatus(Long candidateId, OnboardingStatus onboardingStatus){
        Candidates candidate = candidateRecordsRepository.findById(candidateId).orElseThrow(()-> new CandidateNotFound(candidateId));
        candidate.setOnboardingStatus(onboardingStatus);
        candidateRecordsRepository.save(candidate);

    }


}
