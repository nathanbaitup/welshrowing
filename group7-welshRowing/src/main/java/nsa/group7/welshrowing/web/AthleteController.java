package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AthleteController {

    private AthleteAuditor athleteAuditor;

    @Autowired
    public AthleteController(AthleteAuditor athleteAuditor) {
        this.athleteAuditor = athleteAuditor;
    }
    /**
     * Directs the user to the entry form where when logged in, can enter and save their personal information.
     *
     * @param model - adds to the page model
     * @return returns the athlete-entry-form html
     */

    @GetMapping("new-athlete")
    public String serveAthleteEntryForm(Model model) {
        AthleteForm athleteForm = new AthleteForm();
        model.addAttribute("athlete", athleteForm);
        return "new-athlete-entry";
    }

    /**
     * Creates a new athlete and stores the data, ready to be linked to the database.
     *
     * @param athleteEntry - the object of the athlete entry.
     * @param bindings - the variable that prints to the console if there is an error within the post mapping request.
     * @param model - adds to the page model.
     * @return either returns the athlete to their dashboard or back to the entry form if any errors have occurred.
     */
    @PostMapping("add-athlete")
    public String handleAthleteEntry(@Valid @ModelAttribute("athlete") Athlete athleteEntry, BindingResult bindings, Model model){
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            return "new-athlete-entry";
        } else {
            athleteAuditor.saveAthlete(athleteEntry);
            return "new-athlete-entry";
        }
    }
}
