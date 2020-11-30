package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorkoutController {

    @GetMapping("submit-session-rpe/{athleteID}")
    public String serveSessionRPEForm(@PathVariable Long athleteID, Model model) {
        SessionRPEForm sessionRPEForm = new SessionRPEForm(athleteID);
        model.addAttribute("sessionRPEForm", sessionRPEForm);
        return "session-rpe-form";
    }
}
