package CanditateonboardingSystem.CanditateonboardingSystem.dto;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePersonalInfoDto {

    private Date dob;
    @NotEmpty
    private String gender;
    @NotEmpty
    private String nationality;
    @NotEmpty
    private String address;


}
