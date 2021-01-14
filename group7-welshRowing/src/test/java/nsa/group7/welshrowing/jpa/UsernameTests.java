package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
public class UsernameTests {

    @Autowired
    private ApplicantRepoJPA applicantRepoJPA;

    @Test
    public void shouldAllowANewUsername() throws Exception{
        String username = "nathan";
        Optional<Applicant> possibleUser = Optional.ofNullable(applicantRepoJPA.findByUsername(username));

        if (possibleUser.isEmpty()){
            System.out.println("accept new user");
        } else {
            System.out.println("username already exists.");
        }
    }
}
