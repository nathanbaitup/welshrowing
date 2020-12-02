package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.SessionRPE;
import nsa.group7.welshrowing.domain.SessionRPEAuditor;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final SessionRPEAuditor sessionRPEAuditor;

    @Autowired
    public WorkoutController(SessionRPEAuditor aSessionRPEAuditor) {
        sessionRPEAuditor = aSessionRPEAuditor;
    }

    /**
     * Uses the athleteID to navigate to the session-rpe-form with the id populated.
     *
     * @param athleteID - the athleteID
     * @param model     - updates the form with the needed attributes.
     * @return returns the session-rpe-form.
     */
    @GetMapping("submit-session-rpe/{athleteID}")
    public String serveSessionRPEForm(@PathVariable Long athleteID, Model model) {
        SessionRPEForm sessionRPEForm = new SessionRPEForm(athleteID);
        model.addAttribute("sessionRPEForm", sessionRPEForm);
        return "session-rpe-form";
    }

    /**
     * Submits the athletes session to the database and redirects to the homepage, returns the form if any errors are found.
     *
     * @param sessionRPE - the entity to be stored in the database.
     * @param sessionRPEForm - the user inputs from the form.
     * @param bindings - any resulting errors from filling in the form.
     * @param model - updates the form with needed attributes.
     * @return returns the form if errors or the homepage.
     */
    @PostMapping("submit-session-rpe")
    public String handleSessionRPEForm(@ModelAttribute("sessionRPEForm") SessionRPE sessionRPE, @Valid SessionRPEForm sessionRPEForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            model.addAttribute("sessionRPEForm", sessionRPEForm);
            return "session-rpe-form/" + sessionRPEForm.getAthleteID();
        } else {
            sessionRPEAuditor.saveSession(sessionRPE);
            return "redirect:/";
        }
    }
}
