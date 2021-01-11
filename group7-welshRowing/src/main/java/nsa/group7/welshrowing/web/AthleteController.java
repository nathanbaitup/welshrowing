package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides a set of methods for serving and handling Athlete data.
 */
@Controller
@SessionAttributes(names = {"users"})
public class AthleteController {


    private final AthleteAuditor athleteAuditor;
    private final ApplicantAuditor applicantAuditor;
    private final AnthropometryAuditor anthropometryAuditor;
    private final AthletePreviousSportsAuditor athletePreviousSportsAuditor;
    private final Environment env;

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
    /*
    Injects JavaMailSender class from javax.mail dependency.
         */
    @Autowired
    private JavaMailSender sender;


//    @Autowired
//    private JavaMailSender sender;

    /**
     * Creates an arraylist of user sessions.
     *
     * @return returns a list of current users.
     */
    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
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
     * @param users         - session attribute.
     * @param attributes    - adds the userID to the current session.
     * @param applicantForm - data filled out from the form.
     * @param bindings      - errors from filling out form.
     * @param model         - adds to the page model
     * @return returns either the applicant form if any errors have occurred or redirects to the update details page for the user id.
     */
    @PostMapping("new-applicant")
    public String handleApplicantCreation(@ModelAttribute("applicant") Applicant applicant,
                                          @ModelAttribute("users") List<Long> users,
                                          RedirectAttributes attributes,
                                          @Valid ApplicantForm applicantForm,
                                          BindingResult bindings,
                                          Model model) {
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
            users.add(applicant.getUserID());
            attributes.addFlashAttribute("users", users);
            System.out.println("List of Users: " + users);
            return "redirect:/enter-details/" + users.get(users.size() - 1);
        }
    }

    /**
     * Takes the user id and allows them to update their information.
     *
     * @param id    - the ID of the applicants login credentials.
     * @param users - session attribute.
     * @param model - adds the form to the model
     * @return returns the update details form
     */
    @GetMapping("enter-details/{id}")
    public String serveAthleteEntryForm(@PathVariable Long id, @ModelAttribute("users") List<Long> users, Model model) {
        if (users.get(users.size() - 1).equals(id)) {
            Applicant applicant = applicantAuditor.findApplicantById(id).get();
            AthleteUpdateForm athleteUpdateForm = new AthleteUpdateForm(applicant.getUserID(), applicant.getName());
            model.addAttribute("athleteUpdateForm", athleteUpdateForm);
            return "update-athlete";
        } else {
            return "redirect:/404";
        }
    }

    /**
     * Updates a users data and saves into the database
     *
     * @param athlete           - the entity to be saved to the database.
     * @param athleteUpdateForm - the form where user input data has been entered.
     * @param bindings          - errors from the form.
     * @param users             - session attribute.
     * @param model             - adds to the page model
     * @return returns the form if any errors occur or redirects to the homepage.
     */
    @PostMapping("update-athlete")
    public String handleAthleteEntry(@ModelAttribute("athlete") Athlete athlete, @Valid AthleteUpdateForm athleteUpdateForm,
                                     BindingResult bindings, @ModelAttribute("users") List<Long> users, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("athleteUpdateForm", athleteUpdateForm);
            return "update-athlete";
        } else {
            athleteAuditor.saveAthlete(athlete);
            //Create new mimemessage object using sender method.
            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            //Try to create an email to the applicant about the application process using the email and name stored in the JPA repository.
            try {
                helper.setTo(athlete.getEmail());
                helper.setText("Dear " + athlete.getName() + "\n \n Thank you for your interest in joining the WelshRowing project, to complete your application you must now fill out the forms found on the athlete-dashboard and provide us with more information. Soon, if your application is not rejected, you will be sent information regarding an interview with one of our coaches. If all goes well you will enter the 8 week program as an athlete. \n \n Many thanks, \n The WelshRowing Team");
                helper.setSubject("Welshrowing Application Response");
                //Send the email if no exception is caught.
                sender.send(message);
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            return "redirect:/athlete-dashboard/"+ users.get(users.size() - 1);

        }
    }

    /**
     * @param id    - coach id to check that a coach is signed in.
     * @param users - session attribute
     * @param model - adds to the page
     * @return returns a list of applicants to the coach
     */
    @GetMapping("applicants/{id}")
    public String serveApplicantList(@PathVariable Long id, @ModelAttribute("users") List<Long> users, Model model) {
        Optional<Applicant> findCoach = applicantAuditor.findApplicantById(id);
        Applicant isCoach = findCoach.get();
        if (users.get(users.size() - 1).equals(id) && isCoach.getRole().equals("coach")) {
            List<Athlete> applicantList = athleteAuditor.findAthletesByApplicationStatus(Boolean.TRUE);
            model.addAttribute("listApplicants", applicantList);
            return "applicant-list";
        } else {
            return "redirect:/404";
        }
    }

    @RequestMapping("/applicantToAthlete")
    @ResponseBody
    public String applicantToAthlete(@RequestParam String id, @ModelAttribute("users") List<Long> users, HttpServletRequest request, HttpServletResponse response, Model model) {
        Long athleteID = Long.parseLong(id);
        Optional<Athlete> optionalAthlete = athleteAuditor.findAthleteById(athleteID);
        Athlete athlete = optionalAthlete.get();
        athlete.setApplicationStatus(false);
        athleteAuditor.updateAthlete(athlete);
        return "applicants/" + users.get(users.size() - 1);
    }

    @RequestMapping("/rejectApplicant")
    @ResponseBody
    public String rejectApplicant(@RequestParam String id, @ModelAttribute("users") List<Long> users, HttpServletRequest request, HttpServletResponse response, Model model) {
        Long athleteID = Long.parseLong(id);
        Optional<Athlete> optionalAthlete = athleteAuditor.findAthleteById(athleteID);
        Athlete athlete = optionalAthlete.get();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setTo(athlete.getEmail());
            helper.setText("Dear " + athlete.getName() + "\n \n We thank you greatly for your interest in joining the WelshRowing organisation, however at this moment in time you have been rejected from the program, but we do believe in second-chances so as you continue to improve and train we are open to reassessing your potential and encourage you to reapply. \n \n Many thanks, \n The WelshRowing Team");
            helper.setSubject("Welshrowing Application Response");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        sender.send(message);
        System.out.println(athlete.toString());
        athleteAuditor.deleteAthlete(athleteID);
        return "applicants/" + users.get(users.size() - 1);
    }

    /**
     * Generates the anthropometry form for an athlete to enter their medical data.
     *
     * @param id    - the athlete ID.
     * @param users - session attribute.
     * @param model - adds to the page.
     * @return returns the anthropometry form.
     */
    @GetMapping("submit-anthropometry/{id}")
    public String serveApplicantAnthropometry(@PathVariable("id") Long id, @ModelAttribute("users") List<Long> users, Model model) {

        if (users.get(users.size() - 1).equals(id)) {
            Athlete athlete = athleteAuditor.findAthleteById(id).get();
            AnthropometryForm anthropometryForm = new AnthropometryForm(athlete.getAthleteID());
            model.addAttribute("anthropometry", anthropometryForm);
            return "applicant-anthropometry";
        } else {
            return "redirect:/404";
        }
    }

    /**
     * Returns the anthropometry form with fields already completed to update data.
     *
     * @param id    - the athlete ID.
     * @param users - session attribute.
     * @param model - adds to the page.
     * @return returns the applicant anthropometry form.
     */
    @GetMapping("update-anthropometry/{id}")
    public String serveApplicantUpdateAnthropometryForm(@PathVariable("id") Long id, @ModelAttribute("users") List<Long> users, Model model) {
        if (users.get(users.size() - 1).equals(id)) {
            Anthropometry anthropometry = anthropometryAuditor.decryptData(id, env.getProperty("app.key"));
            Anthropometry anthropometryForm = new Anthropometry(anthropometry.getMedicalDataID(), anthropometry.getAthleteID(), anthropometry.getInjuries(), anthropometry.getHeightCM(), anthropometry.getWeightKG(), anthropometry.getArmspanCM());
            model.addAttribute("anthropometry", anthropometryForm);
            return "applicant-anthropometry";
        } else {
            return "redirect:/404";
        }
    }

    /**
     * Saves an athletes anthropometry data into the database.
     *
     * @param anthropometry     - the anthropometry entity.
     * @param users             - session attribute.
     * @param anthropometryForm - the form data.
     * @param bindings          - any errors if form isn't valid.
     * @param model             - adds to the page.
     * @return returns either the form to append any errors or redirects to the athlete dashboard and saves the data.
     */
    @PostMapping("submit-anthropometry")
    public String handleApplicantAnthropometry(@ModelAttribute("anthropometry") Anthropometry anthropometry, @ModelAttribute("users") List<Long> users, @Valid AnthropometryForm anthropometryForm, BindingResult bindings, Model model) {
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
                return "redirect:/athlete-dashboard/" + users.get(users.size() - 1);
            } else {
                anthropometryAuditor.saveAnthropometricData(anthropometry);
                return "redirect:/athlete-dashboard/" + users.get(users.size() - 1);
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
                return "redirect:/athlete-dashboard/" + users.get(users.size() - 1);
            }
        }
    }

    /**
     * Generates the submit previous sports page for an athlete to enter their previous sport data.
     *
     * @param id    - the athleteID.
     * @param users - session attribute.
     * @param model - adds to the page.
     * @return returns the previous sports form.
     */
    @GetMapping("submit-previous-sports/{id}")
    public String serveApplicantPreviousSports(@PathVariable("id") Long id, @ModelAttribute("users") List<Long> users, Model model) {
        if (users.get(users.size() - 1).equals(id)) {
            Athlete athlete = athleteAuditor.findAthleteById(id).get();
            AthletePreviousSportsForm athletePreviousSportsForm = new AthletePreviousSportsForm(athlete.getAthleteID());
            model.addAttribute("previousSports", athletePreviousSportsForm);
            return "previous-sports";
        } else {
            return "redirect:/404";
        }
    }

    /**
     * Saves an athletes previous sports data into the database.
     *
     * @param athletePreviousSports     - the athlete previous sports entity.
     * @param users                     - session attribute.
     * @param athletePreviousSportsForm - the form data.
     * @param bindings                  - any errors if form isn't valid.
     * @param model                     - adds to the page.
     * @return returns either the form to append any errors or redirects to the athlete dashboard and saves the data.
     */
    @PostMapping("submit-previous-sports")
    public String handlePreviousSports(@ModelAttribute("previousSports") AthletePreviousSports athletePreviousSports, @ModelAttribute("users") List<Long> users, @Valid AthletePreviousSportsForm athletePreviousSportsForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("previousSports", athletePreviousSportsForm);
            return "previous-sports";
        } else {
            athletePreviousSportsAuditor.savePreviousSportsData(athletePreviousSports);
            return "redirect:/athlete-dashboard/" + users.get(users.size() - 1);
        }
    }

    /**
     * Method that hashes the user password and salt.
     *
     * @param password - the text password entered by the user.
     * @return returns a salted and hashed version of the plain text password entered by the user.
     */
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
}
