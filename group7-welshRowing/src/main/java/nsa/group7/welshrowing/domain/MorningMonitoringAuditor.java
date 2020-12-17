package nsa.group7.welshrowing.domain;

import java.util.List;
import java.util.Optional;

/**
 * Provides a set of methods for managing and retrieving Morning Monitoring objects.
 */
public interface MorningMonitoringAuditor {

    public void saveMorningMonitoring(MorningMonitoring morningMonitoring);

    Optional<Applicant> findMorningMonitoringById(Long id);


    public List<MorningMonitoring> findAllMonitoringMonitoring();

    MorningMonitoring findById(Long id);

    MorningMonitoring findById(int id);

    /**
     *
     * @param id - the athleteID.
     * @return returns a morning monitoring object per athleteID.
     */
    List<MorningMonitoring> findByAthleteID(Athlete id);



}
