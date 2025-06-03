package CanditateonboardingSystem.CanditateonboardingSystem.controller;

import CanditateonboardingSystem.CanditateonboardingSystem.dto.JwtResponse;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.LoginRequest;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.MessageResponsesDto;
import CanditateonboardingSystem.CanditateonboardingSystem.dto.SignUpRequest;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.Users;
import CanditateonboardingSystem.CanditateonboardingSystem.service.AuthService;
import CanditateonboardingSystem.CanditateonboardingSystem.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(loginRequest.getUserName());
        Users usersDetails = (Users) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtResponse(
                jwt,
                usersDetails.getId(),
                usersDetails.getUsername(),
                usersDetails.getEmail(),
                usersDetails.getRoleName()
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (authService.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponsesDto("Error: Username is already taken!"));
        }

        authService.registerUser(signUpRequest);

        return ResponseEntity.ok(new MessageResponsesDto("User registered successfully!"));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        authService.sendOtp(email);
        return ResponseEntity.ok("OTP sent successfully!");
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String email, @RequestParam String otp){
        try{
            String jwtToken = authService.verifyOtp(email, otp);
            return ResponseEntity.ok("Otp verified successfully! JWT token: " + jwtToken);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String token, @RequestParam String password){
        try{
            authService.updatePassword(token, password);
            return ResponseEntity.ok("Password updated successfully!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
