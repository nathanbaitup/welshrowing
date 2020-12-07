package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class AthleteController {

    private final AthleteAuditor athleteAuditor;
    private final ApplicantAuditor applicantAuditor;
    private final ApplicantTestingAuditor applicantTestingAuditor;

    @Autowired
    public AthleteController(AthleteAuditor athleteAuditor, ApplicantAuditor applicantAuditor, ApplicantTestingAuditor applicantTestingAuditor) {
        this.athleteAuditor = athleteAuditor;
        this.applicantAuditor = applicantAuditor;
        this.applicantTestingAuditor = applicantTestingAuditor;
    }

    /**
     * Allows a user to create a new account, automatically assumes user is an applicant
     *
     * @param model - adds to the page model
     * @return returns the new applicant form
     */
    @GetMapping("new-applicant")
    public String serveAthleteEntryForm(Model model) {
        ApplicantForm applicantForm = new ApplicantForm(null, null, null, null, "applicant");
        model.addAttribute("applicantForm", applicantForm);
        return "new-applicant";
    }

    /**
     * Saves the name, username and password to the database.
     *
     * @param applicant     - the entity to be stored in the database.
     * @param applicantForm - data filled out from the form.
     * @param bindings      - errors from filling out form.
     * @param model         - adds to the page model
     * @return returns either the applicant form if any errors have occurred or redirects to the update details page for the user id.
     */
    @PostMapping("new-applicant")
    public String handleApplicantCreation(@ModelAttribute("applicant") Applicant applicant, @Valid ApplicantForm applicantForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("applicantForm", applicantForm);
            return "new-applicant";
        } else {
            applicant.setPassword(hashPassword(applicantForm.getPassword()));
            applicantAuditor.saveApplicant(applicant);
            return "redirect:/update-details/" + applicant.getUserID();
        }
    }

    /**
     * Takes the user id and allows them to update their information.
     *
     * @param id    - the ID of the applicants login credentials.
     * @param model - adds the form to the model
     * @return returns the update details form
     */
    @GetMapping("update-details/{id}")
    public String serveAthleteUpdateForm(@PathVariable Long id, Model model) {
        Applicant applicant = applicantAuditor.findApplicantById(id).get();
        AthleteUpdateForm athleteUpdateForm = new AthleteUpdateForm(applicant.getUserID(), applicant.getName());
        model.addAttribute("athleteUpdateForm", athleteUpdateForm);
        return "update-athlete";
    }

    /**
     * Updates a users data and saves into the database
     *
     * @param athlete           - the entity to be saved to the database.
     * @param athleteUpdateForm - the form where user input data has been entered.
     * @param bindings          - errors from the form.
     * @param model             - adds to the page model
     * @return returns the form if any errors occur or redirects to the homepage.
     */
    @PostMapping("update-athlete")
    public String handleAthleteEntry(@ModelAttribute("athlete") Athlete athlete, @Valid AthleteUpdateForm athleteUpdateForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("athleteUpdateForm", athleteUpdateForm);
            return "update-athlete";
        } else {
            athleteAuditor.saveAthlete(athlete);
            return "redirect:/athlete-dashboard";
        }
    }
    @GetMapping("applicants")
    public String serveApplicantList(Model model) {
        List<Athlete> applicantList = athleteAuditor.findAthletesByApplicationStatus(Boolean.TRUE);
        model.addAttribute("listApplicants", applicantList);
        return "applicant-list";
    }

    @GetMapping("submit-testing")
    public String serveApplicantTesting(Model model){
        List<Athlete> applicantList = athleteAuditor.findAthletesByApplicationStatus(Boolean.TRUE);
        model.addAttribute("applicantTesting", applicantList);
        ApplicantTestingForm applicantTestingForm = new ApplicantTestingForm();
        model.addAttribute("applicantList", applicantTestingForm);
        return "applicant-testing";
    }

    @PostMapping("submit-testing")
    public String handleTestingEntry(@Valid @ModelAttribute("applicantList") ApplicantTestingForm applicantTestingForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("applicantList", applicantTestingForm);
            return "applicant-testing";
        } else {
            applicantTestingAuditor.saveApplicantTesting(applicantTestingForm);
            System.out.println(applicantTestingForm);
            return "redirect:/athlete-dashboard";
        }
    }

    /**
     * Method that hashes the user password and salt.
     *
     * @param password - the text password entered by the user.
     * @return returns a salted and hashed version of the plain text password entered by the user.
     */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
