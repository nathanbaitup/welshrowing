package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.ApplicantTesting;
import nsa.group7.welshrowing.domain.Athlete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
public class ApplicantTestingTests {

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;

    @Autowired
    private ApplicantTestingRepo applicantTestingRepo;


    @Test
    public void shouldSubmitApplicantTest() throws Exception {
        Athlete athlete = athleteRepoJPA.findByAthleteID(2L);
        applicantTestingRepo.save(new ApplicantTesting(3L, athlete, "2020-12-09", "should equal this", 1, 1, 1, 1, 1, "Gave Up", "Amber", "", "Amber", ""));
        assertEquals("should equal this", applicantTestingRepo.findByAthleteTestID(3L).getAthleteComments());
    }

    @Test
    public void shouldUpdatePostTestAndApplicationStatus() throws Exception {
        Long athleteID = athleteRepoJPA.findByAthleteID(2L).getAthleteID();

        //Testing that an applicationStatus can be updated.
        athleteRepoJPA.setApplicationStatus(Boolean.TRUE, athleteID); // sets applicationStatus to true before testing to ensure test sees change.
        athleteRepoJPA.setApplicationStatus(Boolean.FALSE, athleteID );
        assertEquals(false, athleteRepoJPA.findByAthleteID(2L).getApplicationStatus());

        //Testing that a postTestResult can be updated.
        athleteRepoJPA.setPostTestStatus("", athleteID); // sets postTestResult to blank before testing to ensure test sees change.
        athleteRepoJPA.setPostTestStatus("START.", athleteID);
        assertEquals("START.", athleteRepoJPA.findByAthleteID(2L).getPostTestResult());
    }


}
