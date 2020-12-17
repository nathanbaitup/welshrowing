package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.NotDoneMorningMonitoring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
public class NotDoneMorningMonitoringTests {

    @Autowired
    private NotDoneMorningMonitoringRepo morningMonitoringRepoJPA;

    @Test
    public void shouldDisplayMorningMonitoringData() throws Exception {
        List<NotDoneMorningMonitoring> allAthletes = morningMonitoringRepoJPA.findAllAthletes();
        System.out.println(allAthletes);
        List<NotDoneMorningMonitoring> completedMorning = morningMonitoringRepoJPA.findCompletedMorningData();
        System.out.println("Done it: " + completedMorning);
        List<NotDoneMorningMonitoring> uncompletedMorningData = new ArrayList<>();

        for (int i = 0; i < allAthletes.size(); i++) {
            if (!(completedMorning.size() == allAthletes.size())){
                completedMorning.add(new NotDoneMorningMonitoring());
            }
        }
        for (NotDoneMorningMonitoring allAthlete : allAthletes) {
            if (!completedMorning.contains(allAthlete)) {
                uncompletedMorningData.add(allAthlete);
            }
        }
        assertEquals(4, uncompletedMorningData.size());
    }
}

