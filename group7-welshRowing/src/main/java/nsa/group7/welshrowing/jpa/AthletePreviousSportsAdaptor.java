package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.AthletePreviousSports;
import nsa.group7.welshrowing.domain.AthletePreviousSportsAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provides a set of methods for managing and retrieving Previous Sporting objects.
 */
@Component
public class AthletePreviousSportsAdaptor implements AthletePreviousSportsAuditor {

    private final AthletePreviousSportsRepo athletePreviousSportsRepo;

    /**
     * Injects the AthletePreviousSportsRepo interface to communicate with the database.
     *
     * @param athletePreviousSportsRepo - the variable depending on the AthletePreviousSports repository to access the database.
     */
    @Autowired
    public AthletePreviousSportsAdaptor(AthletePreviousSportsRepo athletePreviousSportsRepo){
        this.athletePreviousSportsRepo = athletePreviousSportsRepo;
    }

    @Override
    public void savePreviousSportsData(AthletePreviousSports athletePreviousSports) {
        athletePreviousSportsRepo.save(athletePreviousSports);
    }
}
