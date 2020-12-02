package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.MorningMonitoring;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MorningMonitoringController {

    @GetMapping("/athlete-mmd-form")
    public String serveMorningMonitoringForm(Model model) {
        MorningMonitoringForm morningMonitoringForm = new MorningMonitoringForm(null,null,null,0,0,0,0,0,0);
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
            return "athlete-mmd-form";
        } else {
            //applicant.setPassword(hashPassword(applicantForm.getPassword()));
            //applicantAuditor.saveApplicant(applicant);
            System.out.println(morningMonitoringForm.getAthleteID());
            return "athlete-mmd-form";
        }
    }

}
