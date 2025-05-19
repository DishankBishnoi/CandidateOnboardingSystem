package CanditateonboardingSystem.CanditateonboardingSystem.service;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class EmailService {
    private final JavaMailSender mailSender;

    public boolean sendOfferEmail(Candidates candidates){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(candidates.getEmail());
                message.setSubject("Job Offer Notification");
                message.setText("Dear "+candidates.getFirstname()+",\n\n" +
                        "Congrats for getting selected for the job offer. We are very excited to have you on board. Please check your mail for further details");
                mailSender.send(message);
                return true;
        }
        catch (MessagingException e){
            e.printStackTrace();
            return false;

        }
    }

    public boolean SendOtpEmail(String email,String otp){
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("OTP for Canditate Onboarding System");
            message.setText("Your OTP is: "+otp);
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();

        }
    }
}
