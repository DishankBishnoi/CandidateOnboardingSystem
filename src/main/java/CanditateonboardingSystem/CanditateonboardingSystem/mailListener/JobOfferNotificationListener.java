package CanditateonboardingSystem.CanditateonboardingSystem.mailListener;

import CanditateonboardingSystem.CanditateonboardingSystem.Repository.CandidateRecordsRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.Repository.NotificationDetailsRepository;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.Candidates;
import CanditateonboardingSystem.CanditateonboardingSystem.entity.JobOfferNotification;
import CanditateonboardingSystem.CanditateonboardingSystem.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j

public class JobOfferNotificationListener {
    @Autowired
    private final EmailService emailService;

    @Autowired
    private final CandidateRecordsRepository candidateRecordsRepository;

    @Autowired
    private final NotificationDetailsRepository notificationDetailsRepository;


//    This function checks if the candidate has received the job offer email.
    @RabbitListener(queues = "job.offer.queues")
    public boolean processJobOfferMail(Long candidateId){
    try {
        Candidates candidates = candidateRecordsRepository.findById(candidateId)
                .orElseThrow(()-> new RuntimeException("Candidate not found"));

        boolean isEmailSent = emailService.sendOfferEmail(candidates);

        JobOfferNotification notification = JobOfferNotification.builder()
                .candidates(candidates)
                .sent(isEmailSent)
                .sent_at(isEmailSent ? java.time.LocalDateTime.now():null)
                .retry_count(0)
                .build();

        notificationDetailsRepository.save(notification);
        return isEmailSent;
    }
    catch (Exception e){
        log.error("Error sending email to candidate: " + candidateId, e);
        return false;
    }
    }


}
