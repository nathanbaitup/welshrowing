package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AthleteController {
    /**
     *  Directs the user to the entry form where when logged in, can enter and save their personal information.
     * @param model - adds to the page model
     * @return returns the athlete-entry-form html
     */

    @GetMapping("new-athlete")
    public String serveAthleteEntryForm(Model model) {
        AthleteEntryForm athleteEntryForm = new AthleteEntryForm();
        model.addAttribute("athlete", athleteEntryForm);
        return "athlete-entry-form";
    }
}
