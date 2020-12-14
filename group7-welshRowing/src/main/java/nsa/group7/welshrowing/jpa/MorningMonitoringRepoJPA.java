package nsa.group7.welshrowing.jpa;


import nsa.group7.welshrowing.domain.MorningMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Provides a set of methods using the JPA Repository to communicate to the database.
 */
public interface MorningMonitoringRepoJPA extends JpaRepository<MorningMonitoring, Long> {
    MorningMonitoring findByMonitoringID(Long id);
}
