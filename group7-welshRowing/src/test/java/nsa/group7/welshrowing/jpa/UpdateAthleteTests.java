package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class UpdateAthleteTests {

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;


    @Test
    public void shouldAddAthleteToDB() throws Exception {
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, "Nathan Baitup", "Male", "18-06-2001", "nathan@nathan.com", "07123456789", "", "1 Test Street", "CF349BD", "Cardiff University", "", "", "", "", "Facebook", false, ""));
        assertThat(athlete.getAthleteID()).isGreaterThan(0);

    }

    @Test
    public void shouldFindAthleteIDFromName() throws Exception {
        Athlete athlete = athleteRepoJPA.save(new Athlete(2L, "Test Name", "Male", "18-06-2001", "nathan@nathan.com", "07123456789", "", "1 Test Street", "CF349BD", "Cardiff University", "", "", "", "", "Facebook", false, ""));

        Athlete findAthlete = athleteRepoJPA.findByName("Test Name");
        assertEquals(2, findAthlete.getAthleteID());

    }
}
