package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class coachDashboardController {

    private final ApplicantAuditor coachAuditor;
    private final AthleteAuditor athleteAuditor;
    private final ApplicantTestingAuditor applicantTestingAuditor;



    @Autowired
    public coachDashboardController(ApplicantAuditor coachAuditor, AthleteAuditor athleteAuditor,  ApplicantTestingAuditor applicantTestingAuditor) {
        this.coachAuditor = coachAuditor;
        this.athleteAuditor = athleteAuditor;
        this.applicantTestingAuditor = applicantTestingAuditor;
    }

    /**
     *
     * @param id - The ID provided by the database
     * @param name - Name of the ID user
     * @return return the coachDashboard.html file
     */
    @GetMapping("coach-dashboard/{id}")
    public String coachDashboard(@PathVariable Long id, Model name) {
        Applicant aCoachDashboard = coachAuditor.findApplicantById(id).get();
        coachDashboard coachDashboardForm = new coachDashboard (aCoachDashboard.getName(), "Welcome to your dashboard!");;
        name.addAttribute("coachName", coachDashboardForm);

        return "coachDashboard";
    }

    /**
     *
     * @param model
     * @return
     */
    @GetMapping("submit-testing")
    public String serveApplicantTesting(Model model){
        List<Athlete> applicantList = athleteAuditor.findAthletesByApplicationStatus(Boolean.TRUE);
        model.addAttribute("applicantTesting", applicantList);
        ApplicantTesting applicantTesting = new ApplicantTesting();
        model.addAttribute("applicantList", applicantTesting);
        return "applicant-testing";
    }

    /**
     *
     * @param applicantTesting
     * @param bindings
     * @param model
     * @return
     */
    @PostMapping("submit-testing")
    public String handleTestingEntry(@Valid @ModelAttribute("applicantList") ApplicantTesting applicantTesting, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("applicantList", applicantTesting);
            return "applicant-testing";
        } else {
            applicantTestingAuditor.saveApplicantTesting(applicantTesting);
            return "redirect:/coach-dashboard/1";
        }
    }
}
