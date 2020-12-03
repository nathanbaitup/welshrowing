package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepoJPA extends JpaRepository<Applicant, Long> {
    /**
     * Finds a user by userID to return the user object. For use within AthleteController.class.
     *
     * @param id - the user id to search by.
     * @return returns the user object.
     */
    Applicant findByUserID(Long id);

    Applicant findByUsername(String username);
}
