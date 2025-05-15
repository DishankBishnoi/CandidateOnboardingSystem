package CanditateonboardingSystem.CanditateonboardingSystem.controller;

import CanditateonboardingSystem.CanditateonboardingSystem.mailListener.JobOfferNotificationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-offer-notification")
public class JobOfferNotificationController {
    @Autowired
    JobOfferNotificationListener jobOfferNotificationListener;

    @PostMapping("/send-email")
    public boolean sendNotification(@PathVariable  Long candidateId){
        boolean Status = jobOfferNotificationListener.processJobOfferMail(candidateId);
        if(Status){
            return ResponseEntity.ok("Notification Successfully sent");
        }
        else return ResponseEntity.badRequest().body("Error Sending Notification");


    }




}
