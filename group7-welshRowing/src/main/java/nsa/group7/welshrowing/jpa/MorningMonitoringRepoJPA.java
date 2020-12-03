package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.SessionRPE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MorningMonitoringRepoJPA extends JpaRepository<MorningMonitoring, Long > {
    MorningMonitoring findByMorningMonitoringId(Long id);

}
