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
@TestPropertySource(locations = "classpath:application-prod.properties")
@SpringBootTest
public class athleteDashboardTests {
    @Autowired
    private MockMvc mockMvc;

    // connecting to the URL link and checking okay statement
    @Test
    public void athleteDashboardShows() throws Exception {
        this.mockMvc
                .perform(get("/athlete-dashboard/1"))
                .andExpect(status().isOk());


    }
}
