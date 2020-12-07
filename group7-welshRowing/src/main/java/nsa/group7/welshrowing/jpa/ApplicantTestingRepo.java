package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.web.ApplicantTestingForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantTestingRepo extends JpaRepository<ApplicantTestingForm, Long> {
}
