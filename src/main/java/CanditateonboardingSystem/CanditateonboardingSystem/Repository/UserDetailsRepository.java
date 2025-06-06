package CanditateonboardingSystem.CanditateonboardingSystem.Repository;

import CanditateonboardingSystem.CanditateonboardingSystem.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository <Users,Long>{
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
    Optional<Users>findByEmailAndOtp(String email, String otp);
}
