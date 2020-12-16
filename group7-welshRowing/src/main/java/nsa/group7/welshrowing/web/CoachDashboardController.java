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
    private final MorningMonitoringAuditor morningMonitoringAuditor;

    /**
     * Injects all of the auditors needed to save input data into the database.
     *
     * @param coachAuditor            - the ApplicantAuditor.
     * @param athleteAuditor          - the athleteAuditor.
     * @param applicantTestingAuditor - the applicantTesting Auditor.
     */

    @Autowired
    public CoachDashboardController(ApplicantAuditor coachAuditor, AthleteAuditor athleteAuditor, ApplicantTestingAuditor applicantTestingAuditor, MorningMonitoringAuditor morningMonitoringAuditor) {
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
        try {
            Optional<Applicant> findCoach = coachAuditor.findApplicantById(id);
            Applicant isCoach = findCoach.get();
            if (users.get(users.size() - 1).equals(id) && isCoach.getRole().equals("coach")) {
                Applicant aCoachDashboard = coachAuditor.findApplicantById(id).get();
                name.addAttribute("coachID", id);
                name.addAttribute("morningData", viewUncompletedMorningData());
                CoachDashboard coachDashboardForm = new CoachDashboard(aCoachDashboard.getName(), "Welcome to your dashboard!");
                name.addAttribute("coachName", coachDashboardForm);
                return "coachDashboard";
            } else {
                return "redirect:/coach-dashboard/" + users.get(users.size() - 1);
            }
        } catch (Exception e) {
            System.out.println();
            return "redirect:/404";
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


    public List<String> viewUncompletedMorningData(){
        List<String> allAthletes = morningMonitoringAuditor.findAllAthletes();
        List<String> completedMorning = morningMonitoringAuditor.findCompletedMonitoringData();
        List<String> uncompletedMorningData = new ArrayList<>();

        for (int i = 0; i < allAthletes.size(); i++) {
            if (!(completedMorning.size() == allAthletes.size())){
                completedMorning.add(" ");
            }
        }

        for (int i = 0; i < allAthletes.size() ; i++) {
            if (allAthletes.get(i).contains(completedMorning.get(i))){
                System.out.println("already there");
            } else {
                uncompletedMorningData.add(allAthletes.get(i));
            }
        }
        return uncompletedMorningData;
    }
}
