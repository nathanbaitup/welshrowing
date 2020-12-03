package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.MorningMonitoringAuditor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class MorningMonitoringJPAAdapter implements MorningMonitoringAuditor {

    private MorningMonitoringRepoJPA morningMonitoringRepoJPA;

    @Autowired
    public MorningMonitoringJPAAdapter(MorningMonitoringRepoJPA morningMonitoringRepoJPA){
        this.morningMonitoringRepoJPA = morningMonitoringRepoJPA;
    }

    @Override
    public void saveMorningMonitor(MorningMonitoring morningMonitoring) {
        morningMonitoringRepoJPA.save(morningMonitoring);
    }

    public Optional<MorningMonitoring> findByMorningMonitoringId(Long id) {
        return morningMonitoringRepoJPA.findById(id);
    }


}
