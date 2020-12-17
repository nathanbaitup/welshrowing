package nsa.group7.welshrowing.domain;

import java.util.List;
import java.util.Optional;

/**
 * Provides a set of methods for managing and retrieving Morning Monitoring objects.
 */
public interface NotDoneMorningMonitoringAuditor {
    /**
     *
     * @return returns a list of all athletes.
     */
    List<NotDoneMorningMonitoring> findAllAthletes();

    /**
     *
     * @return returns a list of all athletes who have completed morning monitoring.
     */
    List<NotDoneMorningMonitoring> findCompletedMonitoringData();

}
