package nsa.group7.welshrowing.domain;

import java.util.Optional;

public interface crossTrainingAuditor {

    /**
     * Method saving all the infomation to a variable object.
     * @param aCrossTraining - the variable object where all the data is saved.
     */
    public void saveCrossTrainingData(CrossTraining aCrossTraining);

    /**
     * Finding the CrossTraining record in the database via its ID.
     * @param id - The ID of the CrossTraining record.
     * @return returns the record by the ID searched.
     */
    Optional<CrossTraining> findCrossTraining(Long id);
}
