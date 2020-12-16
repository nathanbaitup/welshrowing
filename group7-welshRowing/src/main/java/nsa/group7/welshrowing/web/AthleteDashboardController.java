package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.MorningMonitoringAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Provides a set of methods for serving and handling Athlete data.
 */
@Controller
public class AthleteDashboardController {

    private final MorningMonitoringAuditor graphAuditor;

    @Autowired
    public AthleteDashboardController(MorningMonitoringAuditor graphAuditor){
        this.graphAuditor = graphAuditor;
    }


    @GetMapping("athlete-dashboard")
    public String athleteDashboard(Model model){
        int[] wakingHR = {20,20,20,20,20,20,20};
        int[] standingHR = {30,30,30,30,30,30};
        int[] shape = {40,40,40,40,40,40};

        List<MorningMonitoring> temp = graphAuditor.findAllMonitoringMonitoring();

        for(int i=0; i<7; i++){
            wakingHR[i] = temp.get(i).getWalkingHeartRate();
        }

        AthleteMMDGraphForm graphData = new AthleteMMDGraphForm(wakingHR, standingHR, shape);
        model.addAttribute("graphs",graphData);

        return "athleteDashboard";
    }
}
