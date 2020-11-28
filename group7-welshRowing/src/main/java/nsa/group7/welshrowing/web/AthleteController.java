package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantAuditor;
import nsa.group7.welshrowing.domain.AthleteAuditor;
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
public class AthleteController {

    private AthleteAuditor athleteAuditor;
    private ApplicantAuditor applicantAuditor;

    @Autowired
    public AthleteController(AthleteAuditor athleteAuditor, ApplicantAuditor applicantAuditor) {
        this.athleteAuditor = athleteAuditor;
        this.applicantAuditor = applicantAuditor;
    }

    /**
     * Directs the user to the entry form where when logged in, can create a new account.
     *
     * @param model - adds to the page model
     * @return returns the athlete-entry-form html
     */

    @GetMapping("new-applicant")
    public String serveAthleteEntryForm(Model model) {
        ApplicantForm applicantForm = new ApplicantForm(null,null,null,null,"applicant");
        model.addAttribute("applicant", applicantForm);
        return "new-applicant";
    }

//    @GetMapping("update-details/{id}")
//    public String serveAthleteUpdateForm(@PathVariable Long userID, Model model) {
//        Applicant applicant = applicantAuditor.findApplicantById(userID).get();
//        AthleteUpdateForm athleteUpdateForm = new AthleteUpdateForm(null , applicant.getUserID(), applicant.getName(),"","",true,"","","","","","","","","","","","","",true,"");
//        model.addAttribute("athlete", athleteUpdateForm);
//        return "update-athlete";
//    }

    /**
     *
     * @param applicant
     * @param applicantForm
     * @param bindings
     * @param model
     * @return
     */
    @PostMapping("new-applicant")
    public String handleAthleteEntry(@Valid @ModelAttribute("applicant") Applicant applicant, @Valid ApplicantForm applicantForm, BindingResult bindings, Model model){
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            return "new-applicant";
        } else {
            applicantAuditor.saveApplicant(applicant);
            return "new-applicant";
        }
    }
}
