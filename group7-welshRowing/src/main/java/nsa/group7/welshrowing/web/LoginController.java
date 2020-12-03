package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantAuditor;
import nsa.group7.welshrowing.domain.User;
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

    @GetMapping("/login")
    public String serveLoginPage(Model model){
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginForm",loginForm);
        return "login";
    }

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
