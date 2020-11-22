package nsa.group7.welshrowing.mvc;

import nsa.group7.welshrowing.web.AthleteController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {AthleteController.class})
@WebMvcTest
public class AthleteEntryFormTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGetEntryForm() throws Exception {

        this.mockMvc
                .perform(get("/new-athlete"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Athlete Information entry form.")))
                .andExpect(content().string(containsString("Enter your details here")));
    }
}
