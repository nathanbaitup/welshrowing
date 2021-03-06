package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides a set of methods using the JPA Repository to communicate to the database.
 */
public interface ApplicantRepoJPA extends JpaRepository<Applicant, Long> {
    /**
     * Finds a user by userID to return the user object. For use within AthleteController.class.
     *
     * @param id - the user id to search by.
     * @return returns the user object.
     */
    Applicant findByUserID(Long id);

    /**
     * finds user by username to return user object
     *
     * @param username the username used to search for user object
     * @return returns user object.
     */
    Applicant findByUsername(String username);
}
