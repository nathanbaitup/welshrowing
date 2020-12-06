package nsa.group7.welshrowing.domain;

import java.util.List;
import java.util.Optional;

public interface AthleteAuditor {

    /**
     * Method that saves all information of an athlete object.
     *
     * @param anAthlete - the athlete object to save
     */
    public void saveAthlete(Athlete anAthlete);

    /**
     * Method that allows for an athlete object to be updated.
     *
     * @param anAthlete - the athlete object to be updated
     */
    public void updateAthlete(Athlete anAthlete);

    /**
     * Finds an athlete object based off its ID.
     *
     * @param id - the id of the athlete.
     * @return returns the athlete object.
     */
    Optional<Athlete> findAthleteById(Long id);



    public List<Athlete> findAthletesByApplicationStatus(Boolean anApplicationStatus);

}
