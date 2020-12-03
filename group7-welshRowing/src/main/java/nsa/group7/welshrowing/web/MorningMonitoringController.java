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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MorningMonitoringController {

    private final MorningMonitoringAuditor morningMonitoringAuditor;

    @Autowired
    public MorningMonitoringController(MorningMonitoringAuditor morningMonitoringAuditor) {
        this.morningMonitoringAuditor = morningMonitoringAuditor;
    }

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
            morningMonitoringAuditor.saveMorningMonitoring(morningMonitoring);
            return "athlete-mmd-form";
        }
    }

}
