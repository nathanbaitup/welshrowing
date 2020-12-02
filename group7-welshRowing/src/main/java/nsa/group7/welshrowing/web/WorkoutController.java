package nsa.group7.welshrowing.web;


import nsa.group7.welshrowing.domain.crossTraining;
import nsa.group7.welshrowing.domain.crossTrainingAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WorkoutController {

    private final crossTrainingAuditor crossTrainingAuditor;

    @Autowired
    public WorkoutController(crossTrainingAuditor acrossTrainingAuditor) {
        crossTrainingAuditor = acrossTrainingAuditor;
    }

    @GetMapping("submit-crosstraining-form/{athleteID}")
    public String submitCrossTrainingForm(@PathVariable Long athleteID, Model model) {
        crossTrainingSessionForm crossTrainingSessionForm = new crossTrainingSessionForm(athleteID,null,null,null,null);
        model.addAttribute("crossTrainingSessionForm", crossTrainingSessionForm);
        return "crossTrainingForm";
    }

    @PostMapping("submit-crosstraining-form")
    public String postSubmitCrossTrainingForm(@ModelAttribute("crossTrainingSessionForm") crossTraining crossTraining, @Valid crossTrainingSessionForm crossTrainingSessionForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe: bindings.getAllErrors()){
                System.out.println(oe);
            }
            model.addAttribute("crossTrainingSessionForm", crossTrainingSessionForm);
            return "submit-crosstraining-form/" + crossTrainingSessionForm.getAthleteID();
        } else {
            crossTrainingAuditor.saveCrossTrainingData(crossTraining);
            return "redirect:/";
        }
    }
}
