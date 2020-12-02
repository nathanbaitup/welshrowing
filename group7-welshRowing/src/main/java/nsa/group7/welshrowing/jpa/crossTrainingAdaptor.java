package nsa.group7.welshrowing.jpa;


import nsa.group7.welshrowing.domain.crossTraining;
import nsa.group7.welshrowing.domain.crossTrainingAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class crossTrainingAdaptor implements crossTrainingAuditor {

    private final crossTrainingRepoJPA crossTrainingRepoJPA;

    @Autowired
    public crossTrainingAdaptor(crossTrainingRepoJPA acrossTrainingRepoJPA) {crossTrainingRepoJPA = acrossTrainingRepoJPA; }

    @Override
    public void saveCrossTrainingData(crossTraining acrossTraining) {crossTrainingRepoJPA.save(acrossTraining); }



}
