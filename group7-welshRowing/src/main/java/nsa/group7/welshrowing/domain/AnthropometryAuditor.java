package nsa.group7.welshrowing.domain;

import java.util.Optional;

/**
 * Provides a set of methods for saving and decrypting anthropometric data.
 */
public interface AnthropometryAuditor {
    /**
     * Method that saves all information of an anthropometric object.
     *
     * @param anthropometry - the anthropometry object to save.
     */
    void saveAnthropometricData(Anthropometry anthropometry);

    /**
     * Method that runs the stored procedure to decrypt an athletes medical data if needed to be updated.
     *
     * @param id - the athleteID
     * @param key - the decryption key
     * @return returns decrypted values to be modified.
     */
    Anthropometry decryptData(Long id, String key);
}
