package nsa.group7.welshrowing.web;


import nsa.group7.welshrowing.domain.crossTrainingAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorkoutController {

    private final crossTrainingAuditor crossTrainingAuditor;

    @Autowired
    public WorkoutController(crossTrainingAuditor acrossTrainingAuditor) {
        crossTrainingAuditor = acrossTrainingAuditor;
    }

    @GetMapping("submit-crosstraining-form/{athleteID}")
    public String submitCrossTrainingForm(@PathVariable Long athleteID, Model model) {
        crossTrainingSessionForm crossTrainingSessionForm = new crossTrainingSessionForm(athleteID);
        model.addAttribute("crossTrainingSessionForm", crossTrainingSessionForm);
        return "crossTrainingForm";
    }
}
