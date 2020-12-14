package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.ApplicantTestingAuditor;
import nsa.group7.welshrowing.domain.ApplicantTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicantTestingAdaptor implements ApplicantTestingAuditor {
    private final ApplicantTestingRepo applicantTestingRepo;

    /**
     * Injects the ApplicantTestingRepo interface to communicate with the database.
     *
     * @param applicantTestingRepo - the variable depending on the ApplicantTesting repository to access the database.
     */
    @Autowired
    public ApplicantTestingAdaptor(ApplicantTestingRepo applicantTestingRepo){
        this.applicantTestingRepo = applicantTestingRepo;
    }

    /**
     * Saves the applicantTesting data to the database.
     *
     * @param applicantTesting - the applicantTesting object to save.
     */
    @Override
    public void saveApplicantTesting(ApplicantTesting applicantTesting) {
        applicantTestingRepo.save(applicantTesting);
    }
}
