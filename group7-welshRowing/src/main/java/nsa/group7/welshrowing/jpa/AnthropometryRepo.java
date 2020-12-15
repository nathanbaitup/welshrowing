package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Anthropometry;
import nsa.group7.welshrowing.domain.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

/**
 * Provides a set of methods using the JPA Repository to communicate to the database.
 */
public interface AnthropometryRepo extends JpaRepository<Anthropometry, Long> {
    /**
     * Method that runs the stored procedure to decrypt an athletes medical data if needed to be updated.
     *
     * @param id - the athleteID
     * @param key - the decryption key
     * @return returns decrypted values to be modified.
     */
    @Query(value = "CALL decrypt_data(:theAthleteID, :theKey);", nativeQuery = true)
    Anthropometry decryptData(@Param("theAthleteID") Long id, @Param("theKey") String key);
}
