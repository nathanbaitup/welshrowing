package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("login")
    public String serveAthleteEntryForm() {
        return "login";
    }
}
