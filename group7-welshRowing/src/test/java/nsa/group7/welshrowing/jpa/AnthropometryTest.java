package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Anthropometry;
import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.NotDoneMorningMonitoring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
public class AnthropometryTest {
    @Autowired
    private AthleteRepoJPA athleteRepoJPA;

    @Autowired
    private AnthropometryRepo anthropometryRepo;

    @Autowired
    private Environment env;

    @Test
    public void shouldDisplayDecryptedData() throws Exception {
        Athlete athlete = athleteRepoJPA.findByAthleteID(1L);
        anthropometryRepo.save(new Anthropometry(1L, athlete,"none",100, 98, 100));

        Anthropometry anthropometry = anthropometryRepo.decryptData(1L, env.getProperty("app.key"));
        assertEquals("none", anthropometry.getInjuries());
        assertEquals(98, anthropometry.getWeightKG());

    }
}




