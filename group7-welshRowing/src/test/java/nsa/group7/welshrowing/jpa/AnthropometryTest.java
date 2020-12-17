package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Anthropometry;
import nsa.group7.welshrowing.domain.NotDoneMorningMonitoring;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-prod.properties")
public class AnthropometryTest {
    @Autowired
    private AnthropometryRepo anthropometryRepo;

    @Autowired
    private Environment env;

    @Test
    public void shouldDisplayDecryptedData() throws Exception {
        Anthropometry anthropometry = anthropometryRepo.decryptData(2L, env.getProperty("app.key"));
        assertEquals("hello", anthropometry.getInjuries());

    }
}




