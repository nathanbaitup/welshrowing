package nsa.group7.welshrowing.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest

public class bootstrapTests {
    @Autowired
    private MockMvc mockMvc;

//    Testing for Bootstrap on welcome page
    @Test
    public void ShouldHaveBootstrapWelcome() throws Exception {
        this.mockMvc
                .perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css")));
    }

//    Testing for bootstrap on login page
    @Test
    public void ShouldHaveBootstrapLogin() throws Exception {
        this.mockMvc
                .perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css")));
    }

//    Testing for bootstrap on new Athlete page
    @Test
    public void ShouldHaveBootstrapNewAthlete() throws Exception {
        this.mockMvc
                .perform(get("/new-athlete"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css")));
    }

//    Commented out until merged with coachdashboard creation branch.
//    @Test
//    public void ShouldHaveBootstrapCoachDashboard() throws Exception {
//        this.mockMvc
//                .perform(get("/coachdashboard"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css")));
//    }

}
