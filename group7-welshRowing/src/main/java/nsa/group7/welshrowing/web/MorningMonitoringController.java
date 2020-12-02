package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MorningMonitoringController {

    @GetMapping("/athlete-mmd-form")
    public String serveMorningMonitoringForm(Model model) {
        model.addAttribute(" ");
        return "athlete-mmd-form";
    }

//    @PostMapping("/athlete-mmd-form")
//    public String handleMMDForm()

}
