package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Applicant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class NewApplicantTests {

    @Autowired
    private ApplicantRepoJPA applicantRepoJPA;

    String password = "myPassword1";
    String checkPassword = "thisPassw0rdSh0uldW0rk";

    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    @Test
    public void shouldCreateANewApplicant() {
        applicantRepoJPA.save(new Applicant(null,"Dylan Williams", "dWills", password, "applicant"));
        Applicant savedApplicant = applicantRepoJPA.findByUserID(1L);
        assertEquals("Dylan Williams", savedApplicant.getName());
        assertEquals(password, savedApplicant.getPassword());
    }

    @Test
    public void shouldCreateANewApplicantWithHashedPasswordAndCheckHashIsEqual() {
        applicantRepoJPA.save(new Applicant(2L,"Zac Farro", "zFarro", hashPassword(checkPassword), "applicant"));
        Applicant savedApplicant = applicantRepoJPA.findByUserID(2L);
        assertEquals("Zac Farro", savedApplicant.getName());
        assertNotEquals(checkPassword, savedApplicant.getPassword());
        assertTrue(BCrypt.checkpw(checkPassword, savedApplicant.getPassword()));
    }

}
