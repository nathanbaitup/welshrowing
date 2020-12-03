package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Applicant;
import nsa.group7.welshrowing.domain.ApplicantAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class coachDashboardController {

    private final ApplicantAuditor coachAuditor;

    @Autowired
    public coachDashboardController(ApplicantAuditor coachAuditor) {
        this.coachAuditor = coachAuditor;
    }

    /**
     *
     * @param id - The ID provided by the database
     * @param name - Name of the ID user
     * @return return the coachDashboard.html file
     */
    @GetMapping("coachdashboard/{id}")
    public String coachDashboard(@PathVariable Long id, Model name) {
        Applicant aCoachDashboard = coachAuditor.findApplicantById(id).get();
        coachDashboard coachDashboardForm = new coachDashboard (aCoachDashboard.getName(), "Welcome to your dashboard!");;
        name.addAttribute("coachName", coachDashboardForm);

        return "coachDashboard";
    }
}
