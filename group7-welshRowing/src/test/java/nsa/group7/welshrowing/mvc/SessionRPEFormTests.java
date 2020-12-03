package nsa.group7.welshrowing.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
//REFERENCE accessed 01/12/2020
// https://stackoverflow.com/q/62373679
// although this is the question asked within stack overflow, it does provide the code needed to create a test that links to the database to allow
//for testing of inserting and retrieving of a workout session.
@TestPropertySource(locations = "classpath:application-prod.properties")
//END REFERENCE

@SpringBootTest
public class SessionRPEFormTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldHaveCorrectAthleteID() throws Exception {
        this.mockMvc
                .perform(get("/submit-session-rpe/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("1")));
    }

}