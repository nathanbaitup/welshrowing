package nsa.group7.welshrowing.mvc;


import nsa.group7.welshrowing.jpa.ApplicantRepoJPA;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
@SpringBootTest

public class coachDashboardTest {
    @Autowired
    private MockMvc mockMvc;

// connecting to the URL link and reading webpage for substring provided
    @Test
    public void coachNameShows() throws Exception {
        this.mockMvc
               .perform(get("/coach-dashboard/6")
                       .flashAttr("users", 6))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Coach Name")));
    }

    @Test
    public void coachNameShowsTwo() throws Exception {
        this.mockMvc
                .perform(get("/coach-dashboard/7")
                        .flashAttr("users", 7))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nathan Baitup")));
    }

}
