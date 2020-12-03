package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class morningMonitoringController {

    @GetMapping("coachdashboard/athletemorningmonitoring")
    public String morningMonitoring(Model model) {
            return "athleteMorningMonitoring";

    }
}
