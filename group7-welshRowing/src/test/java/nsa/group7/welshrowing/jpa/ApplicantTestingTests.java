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
    private AthleteJPAAdaptor athleteJPAAdaptor;

    @Autowired
    private ApplicantTestingRepo applicantTestingRepo;


    @Test
    public void shouldSubmitApplicantTest() throws Exception {
        Athlete athlete = athleteJPAAdaptor.findAthleteById(1L).get();
        applicantTestingRepo.save(new ApplicantTesting(1L, athlete, "2020-12-09", "should equal this", 1, 1, 1, 1, 1, "Gave Up", "Amber", "", "Amber", ""));
        assertEquals("should equal this", applicantTestingRepo.findByAthleteTestID(1L).getAthleteComments());
    }

    @Test
    public void shouldUpdatePostTestAndApplicationStatus() throws Exception {
        Long athleteID = athleteJPAAdaptor.findAthleteById(1L).get().getAthleteID();

        //Testing that an applicationStatus can be updated.
        athleteJPAAdaptor.setApplicationStatus(Boolean.TRUE, athleteID); // sets applicationStatus to true before testing to ensure test sees change.
        athleteJPAAdaptor.setApplicationStatus(Boolean.FALSE, athleteID );
        assertEquals(false, athleteJPAAdaptor.findAthleteById(1L).get().getApplicationStatus());

        //Testing that a postTestResult can be updated.
        athleteJPAAdaptor.setPostTestStatus("", athleteID); // sets postTestResult to blank before testing to ensure test sees change.
        athleteJPAAdaptor.setPostTestStatus("START.", athleteID);
        assertEquals("START.", athleteJPAAdaptor.findAthleteById(1L).get().getPostTestResult());
    }


}
