package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.ApplicantTesting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantTestingRepo extends JpaRepository<ApplicantTesting, Long> {
}
