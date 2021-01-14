package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
public class UsernameTests {

    @Autowired
    private ApplicantRepoJPA applicantRepoJPA;

    @Test
    public void shouldSayUsernameAlreadyExists() throws Exception{
        String username = "userbob";
        Optional<Applicant> possibleUser = Optional.ofNullable(applicantRepoJPA.findByUsername(username));
        assertEquals("userbob", possibleUser.get().getUsername());
        assertThat(true);
    }

    @Test
    public void shouldHaveBlankValue() throws Exception{
        String username = "newUsername";
        Optional<Applicant> possibleUser = Optional.ofNullable(applicantRepoJPA.findByUsername(username));
        assertThat(possibleUser.isEmpty());
    }
}
