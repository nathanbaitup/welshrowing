package nsa.group7.welshrowing.domain;

import java.util.List;
import java.util.Optional;

public interface MorningMonitoringAuditor {

    public void saveMorningMonitoring(MorningMonitoring morningMonitoring);

    Optional<Applicant> findMorningMonitoringById(Long id);

    public List<MorningMonitoring> findAllMonitoringMonitoring();

    MorningMonitoring findById(Long id);
}
