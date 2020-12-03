package nsa.group7.welshrowing.jpa;


import nsa.group7.welshrowing.domain.CrossTraining;
import nsa.group7.welshrowing.domain.crossTrainingAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class crossTrainingAdaptor implements crossTrainingAuditor {

    private final crossTrainingRepoJPA crossTrainingRepoJPA;

    @Autowired
    public crossTrainingAdaptor(crossTrainingRepoJPA acrossTrainingRepoJPA) {crossTrainingRepoJPA = acrossTrainingRepoJPA; }

    @Override
    public void saveCrossTrainingData(CrossTraining acrossTraining) {crossTrainingRepoJPA.save(acrossTraining); }

    @Override
    public Optional<CrossTraining> findCrossTraining(Long id) {
        return crossTrainingRepoJPA.findById(id);
    }


}
