package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.*;
import nsa.group7.welshrowing.jpa.MorningMonitoringRepoJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class MorningMonitoringController {

    private final MorningMonitoringRepoJPA morningMonitoringAuditor;

    @Autowired
    public MorningMonitoringController(MorningMonitoringRepoJPA morningMonitoringAuditor) {
        this.morningMonitoringAuditor = morningMonitoringAuditor;
    }

    @GetMapping("coachdashboard/{id}/athletemorningmonitoring")
    public String morningMonitoring(Model model) {
        List<MorningMonitoring> mm = morningMonitoringAuditor.findAll();

        model.addAttribute("morningMonitoring", mm);
            return "athleteMorningMonitoring";

    }


}
