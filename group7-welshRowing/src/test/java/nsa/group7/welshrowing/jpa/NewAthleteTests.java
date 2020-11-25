package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class NewAthleteTests {

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;


    @Test
    public void shouldAddAthleteToDB() throws Exception{
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, "nathan", "male", "2001-02-24", "test@test.com", "test", "07894561230", " ", "2 test lane", " " , "cf12 3hh", "cardiff university", null, null, null, null, "Facebook", false ));
        assertThat(athlete.getAthleteID()).isGreaterThan(0);

    }

    @Test
    public void shouldFindAthleteIDFromName() throws Exception{
        Athlete athlete = athleteRepoJPA.save(new Athlete(2L, "test", "male", "2001-02-24", "test@test.com", "test", "07894561230", " ", "2 test lane", " " , "cf12 3hh", "cardiff university", null, null, null, null, "Facebook", false ));

        Athlete findAthlete = athleteRepoJPA.findByName("test");
    assertEquals(2, findAthlete.getAthleteID());

    }
}
