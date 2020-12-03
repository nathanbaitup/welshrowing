package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class AthleteJPAAdaptor implements AthleteAuditor {

    private final AthleteRepoJPA athleteRepoJPA;

    /**
     * Injects the AthleteRepoJPA interface to communicate with the database.
     *
     * @param anAthleteRepoJPA - the variable depending on the Athlete repository to access the database.
     */
    @Autowired
    public AthleteJPAAdaptor(AthleteRepoJPA anAthleteRepoJPA) {
        athleteRepoJPA = anAthleteRepoJPA;
    }

    /**
     * Uses the athlete auditor interface to access the JPA repository and automatically saves the athlete object within the database.
     *
     * @param anAthlete - the athlete object to save
     */
    @Override
    public void saveAthlete(Athlete anAthlete) {
        athleteRepoJPA.save(anAthlete);
    }

    /**
     * Uses the athlete auditor interface to access the JPA repository and automatically updates the athlete object within the database if the data already exists.
     *
     * @param anAthlete - the athlete object to be updated
     */
    @Override
    public void updateAthlete(Athlete anAthlete) {
        athleteRepoJPA.save(anAthlete);
    }

    /**
     * Uses the athlete auditor to access the JPA repo and finds all data about an athlete given the id.
     *
     * @param id - the id of the athlete.
     * @return returns the athlete object.
     */
    @Override
    public Optional<Athlete> findAthleteById(Long id) {
        return athleteRepoJPA.findById(id);
    }

    @Override
    public List<Athlete> findAthletesByApplicationStatus(Boolean anApplicationStatus) { return athleteRepoJPA.findByApplicationStatus(anApplicationStatus); }
}
