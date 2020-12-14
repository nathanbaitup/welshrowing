package nsa.group7.welshrowing.jpa;


import nsa.group7.welshrowing.domain.CrossTraining;
import nsa.group7.welshrowing.domain.crossTrainingAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Optional;

/**
 * Provides a set of methods for managing and retrieving Cross Training Workout objects.
 */
@Component
public class CrossTrainingAdaptor implements crossTrainingAuditor {

    private final CrossTrainingRepoJPA crossTrainingRepoJPA;

    /**
     * Injecting crossTrainingRepoJPA allowing it to communicate with the database.
     *
     * @param acrossTrainingRepoJPA - Parameter communicates to the repository JPA.
     */
    @Autowired
    public CrossTrainingAdaptor(CrossTrainingRepoJPA acrossTrainingRepoJPA) {crossTrainingRepoJPA = acrossTrainingRepoJPA; }

    /**
     * Uses the auditor to access the reporisity and save the objects within the sessions
     *
     * @param acrossTraining - The object to be saved to the reporisty.
     */
    @Override
    public void saveCrossTrainingData(CrossTraining acrossTraining) {crossTrainingRepoJPA.save(acrossTraining); }

    /**
     * Finding the CrossTraining data line by its id provided
     * @param id - The id of the object
     * @return - Returning the CrossTraining data by the id.
     */
    @Override
    public Optional<CrossTraining> findCrossTraining(Long id) {
        return crossTrainingRepoJPA.findById(id);
    }


}
