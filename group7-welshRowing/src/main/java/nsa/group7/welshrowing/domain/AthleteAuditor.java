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
     * @param anAthlete - the athlete object to be updated.
     */
    public void updateAthlete(Athlete anAthlete);

    /**
     * Finds an athlete object based off its ID.
     *
     * @param id - the id of the athlete.
     * @return returns the athlete object.
     */
    Optional<Athlete> findAthleteById(Long id);

    /**
     * Method that finds all athletes who are in the application status.
     *
     * @param anApplicationStatus the status of the application, should return true.
     * @return returns a list of athletes who are in the training process.
     */
    public List<Athlete> findAthletesByApplicationStatus(Boolean anApplicationStatus);

    /**
     * Method that updates an athletes post test status after completing the test.
     *
     * @param postTestStatus - the string that a coach will give to the applicant based off testing.
     * @param athleteID the ID of the applicant.
     */
    void setPostTestStatus(String postTestStatus, Long athleteID);

    /**
     * Method that updates an athletes application status if they have passed the test requirements.
     *
     * @param applicationStatus - the status of if an athlete is an applicant (true), or if they are not (false).
     * @param athleteID - the ID of the athlete to update.
     */
    void setApplicationStatus(Boolean applicationStatus, Long athleteID);

}
