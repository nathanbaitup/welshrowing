package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Anthropometry;
import nsa.group7.welshrowing.domain.AnthropometryAuditor;
import nsa.group7.welshrowing.domain.Athlete;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

/**
 * Provides a set of methods for saving and decrypting anthropometric data.
 */
@Component
public class AnthropometryAdaptor implements AnthropometryAuditor {

    private final AnthropometryRepo anthropometryRepo;
    /**
     * Injects the AnthropometryRepo interface to communicate with the database.
     *
     * @param anthropometryRepo - the variable depending on the anthropometry repository to access the database.
     */
    @Autowired
    public AnthropometryAdaptor(AnthropometryRepo anthropometryRepo){
        this.anthropometryRepo = anthropometryRepo;
    }

    /**
     * Saves anthropometric data into the database
     *
     * @param anthropometry - the anthropometry object to save.
     */
    @Override
    public void saveAnthropometricData(Anthropometry anthropometry) {
        anthropometryRepo.save(anthropometry);
    }

    /**
     * Method that runs the stored procedure to decrypt an athletes medical data if needed to be updated.
     *
     * @param id - the athleteID
     * @param key - the decryption key
     * @return returns decrypted values to be modified.
     */
    @Override
    public Anthropometry decryptData(Long id, String key) {
        return anthropometryRepo.decryptData(id, key);
    }
}
