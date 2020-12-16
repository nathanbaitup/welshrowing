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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a set of methods for serving and handling Logging in and out data.
 */
@Controller
@SessionAttributes(names = {"users"})
public class LoginController {

    private final ApplicantAuditor applicantAuditor;


    /**
     * Injects all of the auditors needed to save input data into the database.
     *
     * @param applicantAuditor -the applicant Auditor.
     */
    @Autowired
    public LoginController(ApplicantAuditor applicantAuditor) {
        this.applicantAuditor = applicantAuditor;
    }

    /**
     * @return returns a list of users.
     */
    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
    }

    /**
     * Serves the login page to anyone who is directed to /login
     *
     * @param model puts page in model
     * @return returns loginForm
     */
    @GetMapping("/login")
    public String serveLoginPage(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    /**
     * Checks the users login and if correct directs them to the correct page. i.e. coach dashboard/athlete dashboard
     *
     * @param applicant the entity that the user is trying to login as
     * @param loginForm the login information from the form
     * @param users     - session attribute.
     * @param bindings  errors in form
     * @param model     adds page
     * @return if errors occur stays on login page but if password is okay redirects to coach or athlete dashboard depending on role.
     */
    @PostMapping("/login")
    public String handleLoginPage(
            @ModelAttribute("login") Applicant applicant,
            @ModelAttribute("users") List<Long> users,
            RedirectAttributes attributes,
            @Valid LoginForm loginForm,
            BindingResult bindings,
            Model model) {
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
                users.add(theUser.getUserID());
                attributes.addFlashAttribute("users", users);
                System.out.println("List of Users: " + users);
                return "redirect:/coach-dashboard/" + users.get(users.size() - 1);
            } else {
                users.add(theUser.getUserID());
                attributes.addFlashAttribute("users", users);
                System.out.println("List of Users: " + users);
                return "redirect:/athlete-dashboard/" + users.get(users.size() - 1);
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println();
            model.addAttribute("loginForm", loginForm);
            return "login";

        }

    }

    /**
     * Logs the current user out of their session.
     *
     * @param req   - http request.
     * @param model - adds to the page model
     * @return returns the logout page with no current users.
     */
    @GetMapping("logout")
    public String flush(HttpServletRequest req, Model model) {

        model.addAttribute("users", new ArrayList<Long>());

        HttpSession session = req.getSession();
        session.removeAttribute("users");
        session.invalidate();
        System.out.println("List of Users: " + users());
        return "redirect:/logout-page";
    }
}
