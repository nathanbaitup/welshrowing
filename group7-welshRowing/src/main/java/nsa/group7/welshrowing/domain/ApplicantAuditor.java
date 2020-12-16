package nsa.group7.welshrowing.domain;

import java.util.Optional;

/**
 * Provides a set of methods for managing and retrieving Applicant objects.
 */
public interface ApplicantAuditor {
    /**
     * Method that saves all information of an applicant object.
     *
     * @param applicant - the applicant object to save.
     */
    void saveApplicant(Applicant applicant);

    /**
     * Finds a user by userID to return the user object.
     *
     * @param id - the user id to search by.
     * @return returns the user object.
     */
    Optional<Applicant> findApplicantById(Long id);

    /**
     * finds an applicant by their username
     *
     * @param username the username of the applicant being searched for
     * @return the applicant object if found
     */
    Applicant findApplicantByUsername(String username);
}
