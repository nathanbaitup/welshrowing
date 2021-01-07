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
 * Provides a set of methods for serving and handling Coach data.
 */
@Controller
@SessionAttributes(names = {"users"})
public class CoachDashboardController {

    private final ApplicantAuditor coachAuditor;
    private final AthleteAuditor athleteAuditor;
    private final ApplicantTestingAuditor applicantTestingAuditor;
    private final NotDoneMorningMonitoringAuditor morningMonitoringAuditor;

    /**
     * Injects all of the auditors needed to save input data into the database.
     *
     * @param coachAuditor            - the ApplicantAuditor.
     * @param athleteAuditor          - the athleteAuditor.
     * @param applicantTestingAuditor - the applicantTesting Auditor.
     */

    @Autowired
    public CoachDashboardController(ApplicantAuditor coachAuditor, AthleteAuditor athleteAuditor, ApplicantTestingAuditor applicantTestingAuditor, NotDoneMorningMonitoringAuditor morningMonitoringAuditor) {
        this.coachAuditor = coachAuditor;
        this.athleteAuditor = athleteAuditor;
        this.applicantTestingAuditor = applicantTestingAuditor;
        this.morningMonitoringAuditor = morningMonitoringAuditor;
    }

    /**
     * @return returns a list of users.
     */
    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
    }

    /**
     * @param id    - The ID provided by the database
     * @param users - session attribute.
     * @param name  - Name of the ID user
     * @return return the coachDashboard.html file
     */
    @GetMapping("coach-dashboard/{id}")
    public String coachDashboard(@PathVariable Long id, @ModelAttribute("users") List<Long> users, Model name) {
        if (users.get(users.size() - 1).equals(id)) {
            System.out.println("List of Users: " + users);
            Applicant aCoachDashboard = coachAuditor.findApplicantById(id).get();
            CoachDashboard coachDashboardForm = new CoachDashboard(aCoachDashboard.getName(), "Welcome to your dashboard!");
            name.addAttribute("coachName", coachDashboardForm);
            return "coachDashboard";
        } else {
            return "redirect:/coach/coach-dashboard/" + users.get(users.size() - 1);
        }
    }
    /**
     * Directs the coach to the applicant testing form with a drop down list of all current applicants.
     *
     * @param model - adds to the page.
     * @return returns the submit testing form, with the list of current applicants to select from.
     */
    @GetMapping("submit-testing")
    public String serveApplicantTesting(Model model) {
        List<Athlete> applicantList = athleteAuditor.findAthletesByApplicationStatus(Boolean.TRUE);
        model.addAttribute("applicantTesting", applicantList);
        ApplicantTestingForm applicantTestingForm = new ApplicantTestingForm();
        model.addAttribute("applicantList", applicantTestingForm);
        return "applicant-testing";
    }

    /**
     * Submits an applicant test to the database, if they pass the test, they are removed from the applicant list.
     *
     * @param applicantTesting     - the ApplicantTesting entity.
     * @param users                - session attribute.
     * @param applicantTestingForm - the form to take and validate user inputs.
     * @param bindings             -  any errors.
     * @param model                - adds the form to the page given there are errors
     * @return returns either the form to append errors or submits data to the database and redirects to the coach dashboard.
     */
    @PostMapping("submit-testing")
    public String handleTestingEntry(@ModelAttribute("applicantList") ApplicantTesting applicantTesting, @ModelAttribute("users") List<Long> users, @Valid ApplicantTestingForm applicantTestingForm, BindingResult bindings, Model model) {
        if (bindings.hasErrors()) {
            System.out.println("Errors:" + bindings.getFieldErrorCount());
            for (ObjectError oe : bindings.getAllErrors()) {
                System.out.println(oe);
            }
            model.addAttribute("applicantList", applicantTestingForm);
            return "applicant-testing";
        } else if (applicantTestingForm.getPostTestResult().equals("START.") || applicantTestingForm.getPostTestResult().equals("8 weeks")) {
            athleteAuditor.setApplicationStatus(Boolean.FALSE, applicantTestingForm.getAthleteID());
            athleteAuditor.setPostTestStatus(applicantTestingForm.getPostTestResult(), applicantTestingForm.getAthleteID());
            applicantTestingAuditor.saveApplicantTesting(applicantTesting);
            return "redirect:/coach-dashboard/" + users.get(users.size() - 1);
        } else {
            athleteAuditor.setPostTestStatus(applicantTestingForm.getPostTestResult(), applicantTestingForm.getAthleteID());
            applicantTestingAuditor.saveApplicantTesting(applicantTesting);
            return "redirect:/coach-dashboard/" + users.get(users.size() - 1);
        }
    }

    /**
     * Method that compares a list of athletes who have completed their morning monitoring to a list of all athletes, and finds those who haven't done morning monitoring.
     * @return returns a list of those who haven't done morning monitoring.
     */
    public List<NotDoneMorningMonitoring> viewUncompletedMorningData() {
        List<NotDoneMorningMonitoring> allAthletes = morningMonitoringAuditor.findAllAthletes();
        List<NotDoneMorningMonitoring> completedMorning = morningMonitoringAuditor.findCompletedMonitoringData();
        List<NotDoneMorningMonitoring> uncompletedMorningData = new ArrayList<>();

        for (int i = 0; i < allAthletes.size(); i++) {
            if (!(completedMorning.size() == allAthletes.size())) {
                completedMorning.add(new NotDoneMorningMonitoring());
            }
        }
        for (NotDoneMorningMonitoring allAthlete : allAthletes) {
            if (!completedMorning.contains(allAthlete)) {
                uncompletedMorningData.add(allAthlete);
            }
        }
        return uncompletedMorningData;
    }
}
