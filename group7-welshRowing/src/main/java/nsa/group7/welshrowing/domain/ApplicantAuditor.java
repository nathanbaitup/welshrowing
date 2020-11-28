package nsa.group7.welshrowing.domain;

import java.util.Optional;

public interface ApplicantAuditor {
    /**
     *  Method that saves all information of an applicant object.
     * @param applicant - the applicant object to save.
     */
    public void saveApplicant(Applicant applicant);

    Optional<Applicant> findApplicantById(Long id);
}
