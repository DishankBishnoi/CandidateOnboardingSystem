package CanditateonboardingSystem.CanditateonboardingSystem.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@Service
public class CandidateService {


    CandidateRecordsRepository candidateRecordsRepository;
    CandidateBankInformationRepository candidateBankInformationRepository;
    CandidateEducationDetailRepository candidateEducationDetailRepository;
    CandidatePersonalInfoRespository candidatePersonalInfoRespository;


    @Autowired
    public CandidateService(CandidateRecordsRepository candidateRecordsRepository, CandidateBankInformationRepository candidateBankInformationRepository, CandidateEducationDetailRepository candidateEducationDetailRepository, CandidatePersonalInfoRespository candidatePersonalInfoRespository) {
        this.candidateRecordsRepository = candidateRecordsRepository;
        this.candidateBankInformationRepository = candidateBankInformationRepository;
        this.candidateEducationDetailRepository = candidateEducationDetailRepository;
        this.candidatePersonalInfoRespository = candidatePersonalInfoRespository;
    }


    public void addNewCandidate(CandidatesDto CandidateDto){
        Candidates candidate= new Candidates();
        candidate.setFirstname(CandidateDto.getFirstname());
        candidate.setLastname(CandidateDto.getLastname());
        candidate.setPhone(CandidateDto.getPhone());
        candidate.setEmail(CandidateDto.getEmail());
        candidateRecordsRepository.save(candidate);
    }

    public void addPersonalInfo(CandidatePersonalInfoDto CandidatePersonalInfoDto){
        CandidatePersonalInfo candidatePersonalInfo= new CandidatePersonalInfo();
        candidatePersonalInfo.setDob(CandidatePersonalInfoDto.getDob());
        candidatePersonalInfo.setGender(CandidatePersonalInfoDto.getGender());
        candidatePersonalInfo.setNationality(CandidatePersonalInfoDto.getNationality());
        candidatePersonalInfo.setAddress(CandidatePersonalInfoDto.getAddress());
        candidatePersonalInfoRespository.save(candidatePersonalInfo);
    }

    public void saveEducation(CandidateEducationDto candidateEducationDto){
        CandidateEducation candidateEducation = new CandidateEducation();
        candidateEducation.setDegree(CandidateEducationDto.getDegree());

    }
    public int getCandidateCount(){
        int count =0;
        for(Candidates candidates : CandidateRecordsRepository.findAll()){
            count++;
        }
        return count;
    }

}
