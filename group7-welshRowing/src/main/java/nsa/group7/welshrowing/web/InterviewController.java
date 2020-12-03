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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class InterviewController {

    private InterviewAuditor interviewAuditor;

    @Autowired
    public InterviewController(InterviewAuditor interviewAuditor) {
        this.interviewAuditor = interviewAuditor;
    }

    /**
     * Directs the user to the entry form where when logged in, can enter and save their personal information.
     *
     * @param model - adds to the page model
     * @return returns the athlete-entry-form html
     */

    @GetMapping("interview-form/{athleteID}")
    public String serveInterviewForm(@PathVariable Long athleteID, Model model) {
        InterviewForm interviewForm = new InterviewForm(athleteID);
        model.addAttribute("interview", interviewForm);
        return "interview-form";
    }

    /**
     * Creates a new athlete and stores the data, ready to be linked to the database.
     *
     * @param interviewEntry - the object of the interview entry.
     * @param bindings - the variable that prints to the console if there is an error within the post mapping request.
     * @param model - adds to the page model.
     * @return either returns the athlete to their dashboard or back to the entry form if any errors have occurred.
     */
    @PostMapping("enter-interview-form")
    public String handleInterviewEntry(@Valid @ModelAttribute("interview") Interview interviewEntry, BindingResult bindings, Model model){
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            return "interview-form";
        } else {
            interviewAuditor.saveInterview(interviewEntry);
            return "redirect:/coachdashboard/1";
        }
    }





}
