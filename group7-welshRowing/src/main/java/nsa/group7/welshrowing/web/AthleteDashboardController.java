package nsa.group7.welshrowing.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides a set of methods for serving and handling Athlete data.
 */
@Controller
@SessionAttributes(names = {"users"})
public class AthleteDashboardController {
    /**
     *
     * @return
     */
    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
    }

    @GetMapping("athlete-dashboard/{id}")
    public String athleteDashboard(@PathVariable Long id, @ModelAttribute("users") List<Long> users, Model model){
        try {
            if (users.get(users.size() - 1).equals(id)) {
                System.out.println("List of Users: " + users);
                model.addAttribute(" ");
                return "athleteDashboard";
            } else {
                return "redirect:/athlete-dashboard/" + +users.get(users.size() - 1);
            }
        } catch (Exception e){
            System.out.println();
            return "redirect:/404";
        }
    }
}
