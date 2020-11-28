package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepoJPA extends JpaRepository<Applicant, Long> {
    Applicant findByUserID(Long id);
}
