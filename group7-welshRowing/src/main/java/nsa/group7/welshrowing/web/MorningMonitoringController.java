package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Provides a set of methods for serving and handling Morning Monitoring data.
 */
@Controller
@SessionAttributes(names = {"users"})
public class MorningMonitoringController {

    private final MorningMonitoringAuditor morningMonitoringAuditor;
    private final AthleteAuditor athleteAuditor;

    /**
     * @param morningMonitoringAuditor - the morning monitoring auditor.
     */
    @Autowired
    public MorningMonitoringController(MorningMonitoringAuditor morningMonitoringAuditor, AthleteAuditor athleteAuditor) {
        this.morningMonitoringAuditor = morningMonitoringAuditor;
        this.athleteAuditor = athleteAuditor;
    }

    /**
     * @return returns a list of users.
     */
    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
    }

    /**
     * Directs the athlete to the athlete morning monitoring form.
     *
     * @param athleteID - the athleteID
     * @param users     - session attribute.
     * @param model     - adds to the page.
     * @return returns the morning monitoring form
     */
    @GetMapping("/athlete-mmd-form/{athleteID}")
    public String serveMorningMonitoringForm(@PathVariable("athleteID") Long athleteID, @ModelAttribute("users") List<Long> users, Model model) {
        if (users.get(users.size() - 1).equals(athleteID)) {
            MorningMonitoringForm morningMonitoringForm = new MorningMonitoringForm(athleteID, null, 0, 0, 0, 0, 0, 0);
            model.addAttribute("morningMonitoringForm", morningMonitoringForm);
            return "athlete-mmd-form";
        } else {
            return "redirect:/404";
        }
    }

    /**
     * Submits an athlete morning monitoring form.
     *
     * @param morningMonitoring     - the object to save.
     * @param users                 - session attribute.
     * @param morningMonitoringForm - the form to save.
     * @param bindings              - any errors.
     * @param model                 - adds to the page/
     * @return returns the athlete dashboard.
     */
    @PostMapping("/athlete-mmd-form")
    public String handleMMDForm(@ModelAttribute("morningMonitoring") MorningMonitoring morningMonitoring, @ModelAttribute("users") List<Long> users, @Valid MorningMonitoringForm morningMonitoringForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("morningMonitoringForm", morningMonitoringForm);
            return "athlete-mmd-form/" + users.get(users.size() - 1);
        } else {
            morningMonitoringAuditor.saveMorningMonitoring(morningMonitoring);
            return "redirect:/athlete-dashboard/" + users.get(users.size() - 1);
        }
    }

    /**
     * populates morningMonitoringList with the data of the athlete
     *
     * @param id    - the athlete ID.
     * @param users - session attribute.
     * @param model - adds the list of morning monitoring data to the page.
     * @return  returns the morning monitoring list.
     */
    @GetMapping("/morning-monitoring-data/{id}") // Previously morningMonitoring
    public String serveMorningMonitoringList(@PathVariable("id") Long id, @ModelAttribute("users") List<Long> users, Model model) {
        Optional<Athlete> athlete = athleteAuditor.findAthleteById(id);
        Athlete theAthlete = athlete.get();
        if (users.get(users.size() - 1).equals(id)) {
            List<MorningMonitoring> morningMonitoringList = morningMonitoringAuditor.findByAthleteID(theAthlete);
            model.addAttribute("listMorningMonitoring", morningMonitoringList);
            return "coachMorningMonitoring";
        } else {
            return "redirect:/404";
        }
    }

}
