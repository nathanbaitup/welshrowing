package nsa.group7.welshrowing.domain;

import java.util.Optional;

public interface MorningMonitoringAuditor {
    void saveMorningMonitor(MorningMonitoring morningMonitoring);

    Optional<MorningMonitoring> findByMorningMonitoringId(Long id);
}
