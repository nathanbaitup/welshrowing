package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.MorningMonitoringAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MorningMonitoringJPAAdaptor {

    private final MorningMonitoringRepoJPA morningMonitoringRepoJPA;

    @Autowired
    public MorningMonitoringJPAAdaptor(MorningMonitoringRepoJPA aMorningMonitoringRepoJPA) {morningMonitoringRepoJPA = aMorningMonitoringRepoJPA;}

    public void SaveMorningMonitoring(MorningMonitoring morningMonitoring) {morningMonitoringRepoJPA.save(morningMonitoring);}
}
