package nsa.group7.welshrowing.jpa;


import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.NotDoneMorningMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Provides a set of methods using the JPA Repository to communicate to the database.
 */
public interface NotDoneMorningMonitoringRepo extends JpaRepository<NotDoneMorningMonitoring, Long> {

    /**
     *
     * @return returns a list of all athletes who have completed morning monitoring.
     */
    @Query(value = "CALL find_completed_morning_data();", nativeQuery = true)
    List<NotDoneMorningMonitoring> findCompletedMorningData();

    /**
     *
     * @return returns a list of all athletes.
     */
    @Query(value = "CALL find_all_Athletes();", nativeQuery = true)
    List<NotDoneMorningMonitoring> findAllAthletes();

}
