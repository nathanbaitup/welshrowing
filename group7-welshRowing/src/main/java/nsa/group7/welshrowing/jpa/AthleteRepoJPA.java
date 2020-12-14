package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Provides a set of methods using the JPA Repository to communicate to the database.
 */
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
    Optional<Athlete> findById(Long id);

    List<Athlete> findByApplicationStatus(Boolean aApplicationStatus);

    /**
     *Takes a string from the applicant test form and the athlete ID to update the postTestResult field in the athlete table.
     *
     * REFERENCE accessed 09/12/2020
     * https://stackoverflow.com/a/29202504
     * Used to discover @Modifying and @Query annotations to update a singular field in the database using JPA.
     *
     * @param postTestResult - the string that a coach will give to the applicant based off testing.
     * @param athleteID - the ID of the applicant.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Athlete athlete SET athlete.postTestResult = :postTestResult WHERE athlete.athleteID = :athleteID")
    void setPostTestStatus(@Param("postTestResult") String postTestResult ,@Param("athleteID") Long athleteID);
    // END REFERENCE


    /**
     * Takes an ID input to update the application status of an athlete.
     *
     * @param applicationStatus - the status of if an athlete is an applicant (true), or if they are not (false).
     * @param athleteID - the ID of the athlete to update.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Athlete athlete SET athlete.applicationStatus = :applicationStatus WHERE athlete.athleteID = :athleteID")
    void setApplicationStatus(@Param("applicationStatus") Boolean applicationStatus ,@Param("athleteID") Long athleteID);

    Athlete findByAthleteID(Long id);
}
