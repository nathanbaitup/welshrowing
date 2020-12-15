package nsa.group7.welshrowing.domain;

/**
 * Provides a set of methods for managing Applicant Testing objects.
 */
public interface ApplicantTestingAuditor {
    /**
     * Method that saves all information of an applicantTesting object.
     *
     * @param applicantTesting - the applicantTesting object to save.
     */
    void saveApplicantTesting(ApplicantTesting applicantTesting);
}
