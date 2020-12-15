package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Athlete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
public class AcceptApplicantTests {

    @Autowired
    private AthleteRepoJPA athleteRepoJPA;

    public void shouldAcceptApplicant(){
        Athlete athlete = athleteRepoJPA.save(new Athlete(1L, null, "nathan", "male", "2001-02-24", true, "test@test.com", "07894561230", " ", "2 test lane" , "cf12 3hh", "cardiff university", null, null, null, null, "Facebook", false," "));
        athlete.setApplicationStatus(false);
        assertThat(athlete.getApplicationStatus()).isFalse();
    }

}
