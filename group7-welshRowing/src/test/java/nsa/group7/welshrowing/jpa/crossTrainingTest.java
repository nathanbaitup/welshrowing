package nsa.group7.welshrowing.jpa;


import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.CrossTraining;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class crossTrainingTest {

    @Autowired
    private crossTrainingRepoJPA crossTrainingRepoJPA;

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;

    @Test
    public void shouldSaveNewCrossTraining(){
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, null, "Testing", "Male", "17-08-2001", true, "testing@testing.com", "01234567890", "", "1 Test Street", "CF244BX", "Cardiff University", "", "", "", "", "Facebook", false, ""));
       crossTrainingRepoJPA.save(new CrossTraining(2L, athlete, "1998-02-05", "RoadCycle", null, 2));
       List<CrossTraining> crossTrainings = crossTrainingRepoJPA.findAll();
       assertEquals(1, crossTrainings.size());
    }

    @Test
    public void shouldBeAbleToRetrieveCrossTrainingRecord(){
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, null, "Testing", "Male", "17-08-2001", true, "testing@testing.com", "01234567890", "", "1 Test Street", "CF244BX", "Cardiff University", "", "", "", "", "Facebook", false, ""));
        crossTrainingRepoJPA.save(new CrossTraining(1L, athlete, "1998-02-05", "RoadCycle", null, 2));
        CrossTraining aCrossTraining = crossTrainingRepoJPA.findByCrossTrainingId(1L);

        assertEquals("RoadCycle", aCrossTraining.getTypeOfCrossTraining());
        assertEquals("1998-02-05", aCrossTraining.getDateOfSession());

    }
}