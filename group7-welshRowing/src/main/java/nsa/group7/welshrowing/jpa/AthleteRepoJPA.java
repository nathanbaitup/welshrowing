package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AthleteRepoJPA extends JpaRepository<Athlete, Long> {
    /**
     * Finds an athletes name.
     *
     * @param aName the name to find
     * @return returns the athletes name
     */
    Athlete findByName(String aName);

    /**
     * Finds an athlete by their ID.
     *
     * @param id the id to find
     * @return returns the athlete object.
     */
    Athlete findByAthleteID(Long id);

}
