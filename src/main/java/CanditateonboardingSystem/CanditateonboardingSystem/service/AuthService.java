package CanditateonboardingSystem.CanditateonboardingSystem.service;


import CanditateonboardingSystem.CanditateonboardingSystem.Exceptions.UserNotFound;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.UserDetailsRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.SignUpRequest;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.Users;
import CanditateonboardingSystem.CanditateonboardingSystem.util.OtpUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@AllArgsConstructor
public class AuthService {
    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    OtpUtil OtpUtil;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean existsByUserName(String username){
        return userDetailsRepository.findByUsername(username).isPresent();
    }



    public void registerUser(SignUpRequest signUpRequest){
        Users users = new Users();
        users.setUsername(signUpRequest.getUsername());
        users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        users.setEmail(signUpRequest.getEmail());
        users.setRoleName(signUpRequest.getRole());
        userDetailsRepository.save(users);
    }

    public void sendOtp(String email) {
        Users users = userDetailsRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFound(email));

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        users.setOtp(otp);
        users.setOtpExpiryTime(LocalDateTime.now().plusMinutes(5).toLocalTime());
        userDetailsRepository.save(users);

        emailService.SendOtpEmail(email, otp);
    }

    public String verifyOtp(String email, String otp){
        Optional<Users> user = userDetailsRepository.findByEmailAndOtp(email, otp);

        if(user.isPresent() && user.get().getOtpExpiryTime().isAfter(LocalDateTime.now().toLocalTime())){
            return OtpUtil.generateToken(email, 10);
        }

        throw new RuntimeException("Invalid OTP or OTP Expired");
    }


    public void updatePassword(String token,String newPassword){
        String email;
            try {
            email = OtpUtil.validateAndGetEmail(token);
            }
            catch (Exception e){
                throw new RuntimeException("Invalid token");
            }


        Users user = userDetailsRepository.findByEmail(email).orElseThrow(
                ()-> new UserNotFound(email));
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setOtp(null);
        user.setOtpExpiryTime(null);
        userDetailsRepository.save(user);
        }

}

