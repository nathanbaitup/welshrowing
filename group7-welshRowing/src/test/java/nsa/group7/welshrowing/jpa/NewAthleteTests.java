package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import nsa.group7.welshrowing.domain.Interview;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.constraints.AssertTrue;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class NewAthleteTests {

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;
    @Autowired
    private InterviewRepoJPA interviewRepoJPA;


    @Test
    public void shouldAddAthleteToDB() throws Exception{
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, "nathan", "male", "2001-02-24", false,"test@test.com", "test", "07894561230", " ", "2 test lane", " " , "cf12 3hh", "cardiff university", null, null, null, null, "Facebook", false, " "));
        assertThat(athlete.getAthleteID()).isGreaterThan(0);

    }

    @Test
    public void shouldFindAthleteIDFromName() throws Exception{
        Athlete athlete = athleteRepoJPA.save(new Athlete(2L, "test", "male", "2001-02-24", true, "test@test.com", "test", "07894561230", " ", "2 test lane", " " , "cf12 3hh", "cardiff university", null, null, null, null, "Facebook", false ,"" ));

        Athlete findAthlete = athleteRepoJPA.findByName("test");
    assertEquals(2, findAthlete.getAthleteID());

    }
    @Test
    public void shouldAddInterviewToDB () throws Exception{
        Interview interview = interviewRepoJPA.save(new Interview(1L, 3L, "Timak", "jkjsk", "kd", "jfk", "jkkj", "then", 6, 8, 5, 3, "ten", "ho", "ti", 7, 8, 4, 3, 2, 3, 4, 4, 5, 6, 7));
        assertEquals("Timak", interview.getAnswer1());
    }
}
