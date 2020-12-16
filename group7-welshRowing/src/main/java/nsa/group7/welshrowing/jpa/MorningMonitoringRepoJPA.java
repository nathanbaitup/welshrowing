package nsa.group7.welshrowing.jpa;


import nsa.group7.welshrowing.domain.MorningMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Provides a set of methods using the JPA Repository to communicate to the database.
 */
public interface MorningMonitoringRepoJPA extends JpaRepository<MorningMonitoring, Long> {
    MorningMonitoring findByMonitoringID(Long id);

    @Query(value = "CALL find_completed_morning_data();", nativeQuery = true)
    List<String> findCompletedMorningData();

    @Query(value = "CALL find_all_Athletes();", nativeQuery = true)
    List<String> findAllAthletes();

}
