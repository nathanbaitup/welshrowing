package nsa.group7.welshrowing.domain;

import java.util.Optional;

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
}
