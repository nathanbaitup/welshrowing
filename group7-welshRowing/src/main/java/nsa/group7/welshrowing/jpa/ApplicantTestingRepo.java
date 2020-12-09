package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantTesting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantTestingRepo extends JpaRepository<ApplicantTesting, Long> {
    /**
     * Finds an AthleteTest by athleteTestID to return the user object. For use within ApplicantTesting.class.
     *
     * @param id - the AthleteTest id to search by.
     * @return returns the ApplicantTesting object.
     */
    ApplicantTesting findByAthleteTestID(Long id);
}
