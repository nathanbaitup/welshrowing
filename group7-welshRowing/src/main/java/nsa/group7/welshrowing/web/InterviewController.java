package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import nsa.group7.welshrowing.domain.Interview;
import nsa.group7.welshrowing.domain.InterviewAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a set of methods for serving and handling Interview data.
 */
@Controller
@SessionAttributes(names = {"users"})

public class InterviewController {

    private final InterviewAuditor interviewAuditor;
    private final AthleteAuditor athleteAuditor;

    /**
     * Injects all the needed auditors to talk to the database.
     *
     * @param interviewAuditor - the interview Auditor.
     * @param athleteAuditor   - the athlete Auditor.
     */
    @Autowired
    public InterviewController(InterviewAuditor interviewAuditor, AthleteAuditor athleteAuditor) {
        this.interviewAuditor = interviewAuditor;
        this.athleteAuditor = athleteAuditor;
    }

    /**
     * @return returns a list of users.
     */
    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
    }

    /**
     * Directs the user to the interview form
     *
     * @param id    - the coachID.
     * @param users - session attribute.
     * @param model - adds to the page model
     * @return returns the interview form
     */

    @GetMapping("interview-form/{id}")
    public String serveInterviewForm(@PathVariable Long id, @ModelAttribute("users") List<Long> users, Model model) {
        if (users.get(users.size() - 1).equals(id)) {
            System.out.println("List of Users: " + users);
            List<Athlete> applicantList = athleteAuditor.findAthletesByApplicationStatus(Boolean.TRUE);
            model.addAttribute("applicantTesting", applicantList);
            InterviewForm interviewForm = new InterviewForm();
            model.addAttribute("interview", interviewForm);
            return "interview-form";
        } else {
            return "redirect:/404";
        }
    }

    /**
     * Creates a new athlete and stores the data, ready to be linked to the database.
     *
     * @param interviewEntry - the object of the interview entry.
     * @param bindings       - the variable that prints to the console if there is an error within the post mapping request.
     * @param users          - session attribute.
     * @param model          - adds to the page model.
     * @return either returns the athlete to their dashboard or back to the entry form if any errors have occurred.
     */
    @PostMapping("enter-interview-form")
    public String handleInterviewEntry(@Valid @ModelAttribute("interview") Interview interviewEntry, BindingResult bindings, @ModelAttribute("users") List<Long> users, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            return "interview-form";
        } else {
            interviewAuditor.saveInterview(interviewEntry);
            return "redirect:/coach/coach-dashboard/" + users.get(users.size() - 1);
        }
    }


}
