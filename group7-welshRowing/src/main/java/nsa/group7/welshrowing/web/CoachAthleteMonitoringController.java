package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@SessionAttributes(names = {"users"})
public class CoachAthleteMonitoringController {

    private final MorningMonitoringAuditor morningMonitoringAuditor;
    private final ApplicantAuditor applicantAuditor;

    @Autowired
    public CoachAthleteMonitoringController(MorningMonitoringAuditor morningMonitoringAuditor, ApplicantAuditor applicantAuditor) {
        this.morningMonitoringAuditor = morningMonitoringAuditor;
        this.applicantAuditor = applicantAuditor;
    }

    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
    }

    /**
     * redirects to coachmorningmonitoring html page
     * Populates morningMonitoringList with SQL MorningMonitoring table information
     * @param model placeholder which .addAttribute populating with data SQL table
     * @return file name coachMorningMonitoring
     */
    @GetMapping("/coach-morning-monitoring/{id}") // Previously morningMonitoring
    public String serveMorningMonitoringList(@PathVariable("id") Long id, @ModelAttribute("users") List<Long> users, Model model) {
        Optional<Applicant> findCoach = applicantAuditor.findApplicantById(id);
        Applicant isCoach = findCoach.get();
        if (users.get(users.size() - 1).equals(id) && isCoach.getRole().equals("coach")) {
            System.out.println("List of Users: " + users);
            List<MorningMonitoring> morningMonitoringList = morningMonitoringAuditor.findAllMonitoringMonitoring();
            model.addAttribute("listMorningMonitoring", morningMonitoringList);
            return "coachMorningMonitoring";
        } else {
            return "redirect:/404";
        }
    }

}
