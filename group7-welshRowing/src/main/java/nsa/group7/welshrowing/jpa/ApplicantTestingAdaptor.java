package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.ApplicantTestingAuditor;
import nsa.group7.welshrowing.domain.ApplicantTesting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicantTestingAdaptor implements ApplicantTestingAuditor {
    private final ApplicantTestingRepo applicantTestingRepo;

    @Autowired
    public ApplicantTestingAdaptor(ApplicantTestingRepo applicantTestingRepo){
        this.applicantTestingRepo = applicantTestingRepo;
    }

    @Override
    public void saveApplicantTesting(ApplicantTesting applicantTesting) {
        applicantTestingRepo.save(applicantTesting);
    }
}
