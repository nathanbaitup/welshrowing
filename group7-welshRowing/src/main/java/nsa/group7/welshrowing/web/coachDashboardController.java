package nsa.group7.welshrowing.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class coachDashboardController {

    @GetMapping("coachdashboard")
    public String coachDashboard(Model model) {
        coachDashboard aCoachDashboard = new coachDashboard("Adam", "Testing Dynamic Content");
        model.addAttribute("coachName", aCoachDashboard);

        return "coachDashboard";
    }
}
