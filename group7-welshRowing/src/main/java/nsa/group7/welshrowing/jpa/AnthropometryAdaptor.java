package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.Anthropometry;
import nsa.group7.welshrowing.domain.AnthropometryAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
