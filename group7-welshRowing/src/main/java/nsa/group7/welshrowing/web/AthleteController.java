package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Optional;

@Controller
public class AthleteController {

    private final AthleteAuditor athleteAuditor;
    private final ApplicantAuditor applicantAuditor;
    private final AnthropometryAuditor anthropometryAuditor;
    private final AthletePreviousSportsAuditor athletePreviousSportsAuditor;
    private Environment env;

    /**
     * Injects all of the auditors needed to save input data into the database.
     *
     * @param athleteAuditor               - the athleteAuditor.
     * @param applicantAuditor             - the applicantAuditor.
     * @param anthropometryAuditor         - the anthropometryAuditor.
     * @param athletePreviousSportsAuditor - the athletePreviousSportsAuditor.
     * @param env                          - allows to select the key to decrypt medical data
     */
    @Autowired
    public AthleteController(AthleteAuditor athleteAuditor, ApplicantAuditor applicantAuditor, AnthropometryAuditor anthropometryAuditor, AthletePreviousSportsAuditor athletePreviousSportsAuditor, Environment env) {
        this.athleteAuditor = athleteAuditor;
        this.applicantAuditor = applicantAuditor;
        this.anthropometryAuditor = anthropometryAuditor;
        this.athletePreviousSportsAuditor = athletePreviousSportsAuditor;
        this.env = env;
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

    /**
     * @param model - adds to the page
     * @return returns a list of applicants to the coach
     */
    @GetMapping("applicants")
    public String serveApplicantList(Model model) {
        List<Athlete> applicantList = athleteAuditor.findAthletesByApplicationStatus(Boolean.TRUE);
        model.addAttribute("listApplicants", applicantList);
        return "applicant-list";
    }

    @RequestMapping("/applicantToAthlete")
    @ResponseBody
    public String applicantToAthlete(@RequestParam String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        Long athleteID = Long.parseLong(id);
        Optional<Athlete> optionalAthlete = athleteAuditor.findAthleteById(athleteID);
        Athlete athlete = optionalAthlete.get();
        athlete.setApplicationStatus(false);
        athleteAuditor.updateAthlete(athlete);
        return "applicants";
    }

    @RequestMapping("/rejectApplicant")
    @ResponseBody
    public String rejectApplicant(@RequestParam String id, HttpServletRequest request, HttpServletResponse response, Model model) {
        Long athleteID = Long.parseLong(id);
        Optional<Athlete> optionalAthlete = athleteAuditor.findAthleteById(athleteID);
        Athlete athlete = optionalAthlete.get();
        return "applicants";
    }

    /**
     * Generates the anthropometry form for an athlete to enter their medical data.
     *
     * @param id    - the athlete ID.
     * @param model - adds to the page.
     * @return returns the anthropometry form.
     */
    @GetMapping("submit-anthropometry/{id}")
    public String serveApplicantAnthropometry(@PathVariable("id") Long id, Model model) {
        Athlete athlete = athleteAuditor.findAthleteById(id).get();

        AnthropometryForm anthropometryForm = new AnthropometryForm(athlete.getAthleteID());
        model.addAttribute("anthropometry", anthropometryForm);
        return "applicant-anthropometry";
    }

    /**
     * Returns the anthropometry form with fields already completed to update data.
     *
     * @param id    the athlete ID.
     * @param model - adds to the page.
     * @return returns the applicant anthropometry form.
     */
    @GetMapping("update-anthropometry/{id}")
    public String serveApplicantUpdateAnthropometryForm(@PathVariable("id") Long id, Model model) {
        Anthropometry anthropometry = anthropometryAuditor.decryptData(id, env.getProperty("app.key"));
        Anthropometry anthropometryForm = new Anthropometry(anthropometry.getMedicalDataID(), anthropometry.getAthleteID(), anthropometry.getInjuries(), anthropometry.getHeightCM(), anthropometry.getWeightKG(), anthropometry.getArmspanCM());
        model.addAttribute("anthropometry", anthropometryForm);
        return "applicant-anthropometry";
    }

    /**
     * Saves an athletes anthropometry data into the database.
     *
     * @param anthropometry     - the anthropometry entity.
     * @param anthropometryForm - the form data.
     * @param bindings          - any errors if form isn't valid.
     * @param model             - adds to the page.
     * @return returns either the form to append any errors or redirects to the athlete dashboard and saves the data.
     */
    @PostMapping("submit-anthropometry")
    public String handleApplicantAnthropometry(@ModelAttribute("anthropometry") Anthropometry anthropometry, @Valid AnthropometryForm anthropometryForm, BindingResult bindings, Model model) {
        try {
            Anthropometry medicalID = anthropometryAuditor.decryptData(anthropometryForm.getAthleteID(), env.getProperty("app.key"));
            if (bindings.hasErrors()) {
                System.out.println("Errors:" + bindings.getFieldErrorCount());
                for (ObjectError oe : bindings.getAllErrors()) {
                    System.out.println(oe);
                }
                model.addAttribute("anthropometry", anthropometryForm);
                return "applicant-anthropometry";
            } else if (anthropometryForm.getAthleteID().equals(medicalID.getAthleteID().getAthleteID())) {
                anthropometry.setMedicalDataID(medicalID.getMedicalDataID());
                anthropometryAuditor.saveAnthropometricData(anthropometry);
                return "redirect:/athlete-dashboard";
            } else {
                anthropometryAuditor.saveAnthropometricData(anthropometry);
                return "redirect:/athlete-dashboard";
            }
        } catch (NullPointerException npe) {

            if (bindings.hasErrors()) {
                System.out.println("Errors:" + bindings.getFieldErrorCount());
                for (ObjectError oe : bindings.getAllErrors()) {
                    System.out.println(oe);
                }
                model.addAttribute("anthropometry", anthropometryForm);
                return "applicant-anthropometry";
            } else {
                anthropometryAuditor.saveAnthropometricData(anthropometry);
                return "redirect:/athlete-dashboard";
            }
        }
    }

    /**
     * Generates the submit previous sports page for an athlete to enter their previous sport data.
     *
     * @param id    - the athleteID.
     * @param model - adds to the page.
     * @return returns the previous sports form.
     */
    @GetMapping("submit-previous-sports/{id}")
    public String serveApplicantPreviousSports(@PathVariable("id") Long id, Model model) {
        Athlete athlete = athleteAuditor.findAthleteById(id).get();

        AthletePreviousSportsForm athletePreviousSportsForm = new AthletePreviousSportsForm(athlete.getAthleteID());
        model.addAttribute("previousSports", athletePreviousSportsForm);
        return "previous-sports";
    }

    /**
     * Saves an athletes previous sports data into the database.
     *
     * @param athletePreviousSports     - the athlete previous sports entity.
     * @param athletePreviousSportsForm - the form data.
     * @param bindings                  - any errors if form isn't valid.
     * @param model                     - adds to the page.
     * @return returns either the form to append any errors or redirects to the athlete dashboard and saves the data.
     */
    @PostMapping("submit-previous-sports")
    public String handlePreviousSports(@ModelAttribute("previousSports") AthletePreviousSports athletePreviousSports, @Valid AthletePreviousSportsForm athletePreviousSportsForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("previousSports", athletePreviousSportsForm);
            return "previous-sports";
        } else {
            athletePreviousSportsAuditor.savePreviousSportsData(athletePreviousSports);
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
