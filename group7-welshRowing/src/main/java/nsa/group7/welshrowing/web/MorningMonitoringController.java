package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.MorningMonitoringAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Provides a set of methods for serving and handling Morning Monitoring data.
 */
@Controller
public class MorningMonitoringController {

    private final MorningMonitoringAuditor morningMonitoringAuditor;

    @Autowired
    public MorningMonitoringController(MorningMonitoringAuditor morningMonitoringAuditor) {
        this.morningMonitoringAuditor = morningMonitoringAuditor;
    }

    @GetMapping("/athlete-mmd-form/{athleteID}")
    public String serveMorningMonitoringForm(@PathVariable("athleteID") Long athleteID, Model model) {
        MorningMonitoringForm morningMonitoringForm = new MorningMonitoringForm(athleteID,null,0,0,0,0,0,0);
        model.addAttribute("morningMonitoringForm",morningMonitoringForm);
        return "athlete-mmd-form";
    }

    @PostMapping("/athlete-mmd-form")
    public String handleMMDForm(@ModelAttribute("morningMonitoring") MorningMonitoring morningMonitoring, MorningMonitoringForm morningMonitoringForm, BindingResult bindings, Model model){
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("morningMonitoringForm", morningMonitoringForm);
            return "athlete-mmd-form/" + morningMonitoringForm.getAthleteID();
        } else {
            morningMonitoringAuditor.saveMorningMonitoring(morningMonitoring);
            return "redirect:/athlete-dashboard";
        }
    }

}
