package CanditateonboardingSystem.CanditateonboardingSystem.Exceptions;

public class UserNotFound extends RuntimeException {
    public UserNotFound(String email) {
        super("User not found for id : " + email + "does not exist");
    }
}
