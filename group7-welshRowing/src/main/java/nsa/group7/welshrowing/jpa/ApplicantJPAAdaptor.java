package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicantJPAAdaptor implements ApplicantAuditor {

    private final ApplicantRepoJPA applicantRepoJPA;

    /**
     * Injects the ApplicantRepoJPA interface to communicate with the database.
     *
     * @param anApplicantRepoJPA - the variable depending on the Applicant repository to access the database.
     */
    @Autowired
    public ApplicantJPAAdaptor(ApplicantRepoJPA anApplicantRepoJPA) {
        applicantRepoJPA = anApplicantRepoJPA;
    }

    /**
     * Saves an applicant object into the database.
     *
     * @param applicant - the applicant object to save.
     */
    @Override
    public void saveApplicant(Applicant applicant) {
        applicantRepoJPA.save(applicant);
    }

    /**
     * Finds a user by userID to return the user object.
     *
     * @param id - the user id to search by.
     * @return returns the user object.
     */
    @Override
    public Optional<Applicant> findApplicantById(Long id) {
        return applicantRepoJPA.findById(id);
    }
}
