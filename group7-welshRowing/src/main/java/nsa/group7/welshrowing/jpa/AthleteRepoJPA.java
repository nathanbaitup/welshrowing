package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AthleteRepoJPA extends JpaRepository<Athlete, Long> {
    /**
     *
     * @param aName the name to find
     * @return returns the athletes name
     */
    Athlete findByName(String aName);
}
