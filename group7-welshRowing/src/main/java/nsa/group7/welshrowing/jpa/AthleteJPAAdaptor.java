package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AthleteJPAAdaptor implements AthleteAuditor {

    private final AthleteRepoJPA athleteRepoJPA;

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
     * Uses the athlete auditor interface to access the JPA repository and automatically updates the athlete object within the database if the data already exists..
     *
     * @param anAthlete - the athlete object to be updated
     */
    @Override
    public void updateAthlete(Athlete anAthlete) {
        athleteRepoJPA.save(anAthlete);
    }

}
