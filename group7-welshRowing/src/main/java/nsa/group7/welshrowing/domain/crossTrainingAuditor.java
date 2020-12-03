package nsa.group7.welshrowing.domain;

import java.util.Optional;

public interface crossTrainingAuditor {


    public void saveCrossTrainingData(CrossTraining aCrossTraining);

    Optional<CrossTraining> findCrossTraining(Long id);
}
