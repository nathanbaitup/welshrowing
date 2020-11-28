package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicantJPAAdaptor implements ApplicantAuditor {

    private final ApplicantRepoJPA applicantRepoJPA;

    @Autowired
    public ApplicantJPAAdaptor(ApplicantRepoJPA anApplicantRepoJPA) {
        applicantRepoJPA = anApplicantRepoJPA;
    }

    @Override
    public void saveApplicant(Applicant applicant) {
        applicantRepoJPA.save(applicant);
    }

    @Override
    public Optional<Applicant> findApplicantById(Long id) {
        return applicantRepoJPA.findById(id);
    }
}
