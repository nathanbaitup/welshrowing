package nsa.group7.welshrowing.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
@DataJpaTest

public class coachDashboardTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void coachNameShows() throws Exception {
        this.mockMvc
                .perform(get("/coachdashboard/6"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Testing Person")));
    }

}
