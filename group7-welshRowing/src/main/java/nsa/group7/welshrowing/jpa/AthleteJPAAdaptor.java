package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Provides a set of methods for managing and retrieving Athlete objects.
 */
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

    /**
     * Finds a list of all athletes who are in the application stage of their trial.
     *
     * @param anApplicationStatus the status of the application, should return true.
     * @return returns a list of current applicants in the system.
     */
    @Override
    public List<Athlete> findAthletesByApplicationStatus(Boolean anApplicationStatus) { return athleteRepoJPA.findByApplicationStatus(anApplicationStatus); }

    /**
     * Takes a string from the applicant test form and the athlete ID to update the postTestResult field in the athlete table.
     *
     * @param postTestStatus - the string that a coach will give to the applicant based off testing.
     * @param athleteID - the ID of the applicant.
     */
    @Override
    public void setPostTestStatus(String postTestStatus, Long athleteID) {
        athleteRepoJPA.setPostTestStatus(postTestStatus, athleteID);
    }

    /**
     * Takes an ID input to update the application status of an athlete.
     *
     * @param applicationStatus - the status of if an athlete is an applicant (true), or if they are not (false).
     * @param athleteID - the ID of the athlete to update.
     */
    @Override
    public void setApplicationStatus(Boolean applicationStatus, Long athleteID) {
        athleteRepoJPA.setApplicationStatus(applicationStatus, athleteID);
    }

}
