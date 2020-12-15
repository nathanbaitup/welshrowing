package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.MorningMonitoringAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CoachAthleteMonitoringController {

    private final MorningMonitoringAuditor morningMonitoringAuditor;

    @Autowired
    public CoachAthleteMonitoringController(MorningMonitoringAuditor morningMonitoringAuditor) {
        this.morningMonitoringAuditor = morningMonitoringAuditor;
    }

//    @GetMapping("/coachdashboard/coachmorningmonitoring")
//    public String athleteMonitoring(){
//
//        return "coachMorningMonitoring";
//
//    }

    /**
     * redirects to coachmorningmonitoring html page
     * Populates morningMonitoringList with SQL MorningMonitoring table information
     * @param model placeholder which .addAttribute populating with data SQL table
     * @return file name coachMorningMonitoring
     */
    @GetMapping("/coachdashboard/coachmorningmonitoring") // Previously morningMonitoring
    public String serveMorningMonitoringList(Model model) {
        List<MorningMonitoring> morningMonitoringList = morningMonitoringAuditor.findAllMonitoringMonitoring();
        model.addAttribute("listMorningMonitoring", morningMonitoringList);
        return "coachMorningMonitoring";
    }

}
