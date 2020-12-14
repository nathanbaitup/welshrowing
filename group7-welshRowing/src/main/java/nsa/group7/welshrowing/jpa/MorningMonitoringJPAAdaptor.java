package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantAuditor;
import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.MorningMonitoringAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MorningMonitoringJPAAdaptor implements MorningMonitoringAuditor {

    private final MorningMonitoringRepoJPA morningMonitoringRepoJPA;

    @Autowired
    public MorningMonitoringJPAAdaptor(MorningMonitoringRepoJPA aMorningMonitoringRepoJPA) {morningMonitoringRepoJPA = aMorningMonitoringRepoJPA;}


    @Override
    public void saveMorningMonitoring(MorningMonitoring morningMonitoring) {
        System.out.println(morningMonitoring.getMonitoringID());
        morningMonitoringRepoJPA.save(morningMonitoring);
    }

    @Override
    public Optional<Applicant> findMorningMonitoringById(Long id) {
        return Optional.empty();
    }

    /**
     * Creating a new list based off MorningMonitoring SQL Table
     * findAll() is an extension of the JPARepository
     * @return Returning morningMonitoringRepoJPA.findAll()
     */
    public List<MorningMonitoring> findAllMonitoringMonitoring() {
        return morningMonitoringRepoJPA.findAll();

    }

    @Override
    public MorningMonitoring findById(Long id) {
        return null;
    }

}

