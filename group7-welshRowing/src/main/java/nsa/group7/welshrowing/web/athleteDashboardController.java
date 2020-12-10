package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class athleteDashboardController {
    @GetMapping("athlete-dashboard")
    public String athleteDashboard(Model model){
        model.addAttribute(" ");
        return "athleteDashboard";
    }
}
