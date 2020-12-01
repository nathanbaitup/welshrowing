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


    @Test
    public void coachNameShows() throws Exception {
        this.mockMvc
               .perform(get("/coachdashboard/1"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nathan Test")));
    }

    @Test
    public void coachNameShowsTwo() throws Exception {
        this.mockMvc
                .perform(get("/coachdashboard/2"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Adam Jones")));
    }

    @Test
    public void coachNameShowsThree() throws Exception {
        this.mockMvc
                .perform(get("/coachdashboard/3"))
//                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Oliver Twist")));
    }

}
