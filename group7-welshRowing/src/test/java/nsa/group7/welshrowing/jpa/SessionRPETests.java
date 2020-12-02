package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.SessionRPE;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class SessionRPETests {

    @Autowired
    private SessionRPERepoJPA sessionRPERepoJPA;

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;

    @Test
    public void shouldSaveANewWorkoutSession(){
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, "Nathan Baitup", "Male", "18-06-2001", "nathan@nathan.com", "07123456789", "", "1 Test Street", "CF349BD", "Cardiff University", "", "", "", "", "Facebook", false, ""));
        sessionRPERepoJPA.save(new SessionRPE(1L, athlete,"2020-12-02","Yoga",5,80));
        List<SessionRPE> sessions = sessionRPERepoJPA.findAll();
        assertEquals(1, sessions.size());
    }

    @Test
    public void shouldBeAbleToRetrieveASessionAndSessionDate(){
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, "Nathan Baitup", "Male", "18-06-2001", "nathan@nathan.com", "07123456789", "", "1 Test Street", "CF349BD", "Cardiff University", "", "", "", "", "Facebook", false, ""));
        sessionRPERepoJPA.save(new SessionRPE(1L, athlete,"2020-12-25","Circuit",6,60));
        SessionRPE aSession = sessionRPERepoJPA.findBySessionRPEID(1L);

        assertEquals("Circuit", aSession.getTypeOfSession());
        assertEquals("2020-12-25", aSession.getDateOfSession());
    }

}
