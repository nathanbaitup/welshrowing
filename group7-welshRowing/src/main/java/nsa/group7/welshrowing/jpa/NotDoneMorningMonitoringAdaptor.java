package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * Provides a set of methods for managing and retrieving Morning Monitoring objects.
 */
@Component
public class NotDoneMorningMonitoringAdaptor implements NotDoneMorningMonitoringAuditor {

    private final NotDoneMorningMonitoringRepo morningMonitoringRepoJPA;

    /**
     * Injects the NotDoneMorningMonitoring repo.
     *
     * @param aMorningMonitoringRepoJPA - the repo.
     */
    @Autowired
    public NotDoneMorningMonitoringAdaptor(NotDoneMorningMonitoringRepo aMorningMonitoringRepoJPA) {morningMonitoringRepoJPA = aMorningMonitoringRepoJPA;}

    /**
     *
     * @return returns a list of all athletes.
     */
    @Override
    public List<NotDoneMorningMonitoring> findAllAthletes() {
        return morningMonitoringRepoJPA.findAllAthletes();
    }

    /**
     *
     * @return returns a list of all athletes who have completed morning monitoring.
     */
    @Override
    public List<NotDoneMorningMonitoring> findCompletedMonitoringData() {
        return morningMonitoringRepoJPA.findCompletedMorningData();
    }


}

