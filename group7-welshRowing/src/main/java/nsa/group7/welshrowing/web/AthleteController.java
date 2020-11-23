package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AthleteController {
    /**
     * Directs the user to the entry form where when logged in, can enter and save their personal information.
     *
     * @param model - adds to the page model
     * @return returns the athlete-entry-form html
     */

    @GetMapping("new-athlete")
    public String serveAthleteEntryForm(Model model) {
        AthleteEntryForm athleteEntryForm = new AthleteEntryForm();
        model.addAttribute("athlete", athleteEntryForm);
        return "new-athlete-entry";
    }

    @PostMapping("add-athlete")
    public String handleAthleteEntry(@Valid @ModelAttribute("athlete") AthleteEntryForm aForm, BindingResult bindings, Model model){
        System.out.println("New Athlete: " + aForm);
        return "new-athlete-entry";
    }
}
