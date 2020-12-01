package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.SessionRPE;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class WorkoutController {

    @GetMapping("submit-session-rpe/{athleteID}")
    public String serveSessionRPEForm(@PathVariable Long athleteID, Model model) {
        SessionRPEForm sessionRPEForm = new SessionRPEForm(athleteID);
        model.addAttribute("sessionRPEForm", sessionRPEForm);
        return "session-rpe-form";
    }

    @PostMapping("submit-session-rpe")
    public String handleSessionRPEForm(@ModelAttribute("sessionRPEForm") SessionRPE sessionRPE, @Valid SessionRPEForm sessionRPEForm, BindingResult bindingResult, Model model){
        return "redirect:/";
    }
}
