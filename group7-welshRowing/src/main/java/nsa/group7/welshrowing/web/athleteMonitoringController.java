package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class athleteMonitoringController {

    @GetMapping("/coachdashboard/athletemonitoring")
    public String athleteMonitoring(){

        return "athleteMorningMonitoring";

    }



}
