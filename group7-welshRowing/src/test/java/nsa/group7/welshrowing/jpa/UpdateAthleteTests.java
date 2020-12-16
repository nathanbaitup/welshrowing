package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UpdateAthleteTests {

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;


    @Test
    public void shouldAddAthleteToDB() throws Exception {
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, null, "Nathan Baitup", "Male", "18-06-2001", true, "nathan@nathan.com", "07123456789", "", "1 Test Street", "CF349BD", "Cardiff University", "", "", "", "", "Facebook", false, ""));
        assertThat(athlete.getAthleteID()).isGreaterThan(0);

    }

    @Test
    public void shouldFindAthleteIDFromName() throws Exception {
        Athlete athlete = athleteRepoJPA.save(new Athlete(2L, null, "Test Name", "Male", "18-06-2001", true, "nathan@nathan.com", "07123456789", "", "1 Test Street", "CF349BD", "Cardiff University", "", "", "", "", "Facebook", false, ""));

        Athlete findAthlete = athleteRepoJPA.findByName("Test Name");
        assertEquals(2, findAthlete.getAthleteID());

    }

    @Test
    public void shouldFindApplicantList () throws Exception {
        Athlete athlete = athleteRepoJPA.save(new Athlete(3L, null, "Tim Tom", "Male", "20-05-2000", true, "tim@tom.com", "9094043", "904920492", "1 Test Street", "CF42 YES", " ", " ", " ", "930913019", " ", " ", false, " "));
        List<Athlete> applicantList = athleteRepoJPA.findByApplicationStatus(true);
        System.out.println(applicantList);
        assertThat(applicantList.contains(athlete));
    }
}
