package nsa.group7.welshrowing.mvc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@SpringBootTest
public class mmdTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void mmdFormShows() throws Exception {
        this.mockMvc
                .perform(get("/athlete-mmd-form/2")
                        .flashAttr("users", 2))
                .andExpect(status().isOk());
    }
}
