package nsa.group7.welshrowing.jpa;

import nsa.group7.welshrowing.domain.CrossTraining;
import org.springframework.data.jpa.repository.JpaRepository;

public interface crossTrainingRepoJPA extends JpaRepository<CrossTraining, Long> {
    /**
     *
     * @param id - ID of the Athletes session
     * @return - Returns the cross-training with the athletes ID as a match
     */
    CrossTraining findByCrossTrainingId(Long id);
}
