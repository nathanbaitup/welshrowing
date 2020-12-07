package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    private final ApplicantAuditor applicantAuditor;

    @Autowired
    public LoginController(ApplicantAuditor applicantAuditor){
        this.applicantAuditor = applicantAuditor;
    }

    /**
     * Serves the login page to anyone who is directed to /login
     *
     * @param model puts page in model
     * @return returns loginForm
     */
    @GetMapping("/login")
    public String serveLoginPage(Model model){
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm",loginForm);
        return "login";
    }

    /**
     * Checks the users login and if correct directs them to the correct page. i.e. coach dashboard/athlete dashboard
     *
     * @param applicant the entity that the user is trying to login as
     * @param loginForm the login information from the form
     * @param bindings errors in form
     * @param model adds page
     * @return if errors occur stays on login page but if password is okay redirects to coach or athlete dashboard depending on role.
     */
    @PostMapping("/login")
    public String handleLoginPage(@ModelAttribute("login") Applicant applicant, @Valid LoginForm loginForm, BindingResult bindings, Model model){
        try {
            Applicant theUser = applicantAuditor.findApplicantByUsername(loginForm.getUsername());

            if (bindings.hasErrors()) {
                System.out.println("Errors:" + bindings.getFieldErrorCount());
                for (ObjectError oe : bindings.getAllErrors()) {
                    System.out.println(oe);
                }
                model.addAttribute("loginForm", loginForm);
                return "login";
            } else if (!BCrypt.checkpw(loginForm.getPassword(), theUser.getPassword())) {
                model.addAttribute("loginForm", loginForm);
                return "login";
            } else if (theUser.getRole().equals("coach")) {
                return "redirect:/coachdashboard/" + theUser.getUserID();
            } else {
                return "redirect:/athlete-dashboard";
            }
        } catch (Exception e){
            System.out.println(e);
            System.out.println();
            model.addAttribute("loginForm",loginForm);
            return "login";

        }
    }
}
