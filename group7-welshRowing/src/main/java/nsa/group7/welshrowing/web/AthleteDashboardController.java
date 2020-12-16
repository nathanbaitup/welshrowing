package nsa.group7.welshrowing.web;

import nsa.group7.welshrowing.domain.Athlete;
import nsa.group7.welshrowing.domain.AthleteAuditor;
import nsa.group7.welshrowing.domain.MorningMonitoring;
import nsa.group7.welshrowing.domain.MorningMonitoringAuditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * Provides a set of methods for serving and handling Athlete data.
 */
@Controller
@SessionAttributes(names = {"users"})
public class AthleteDashboardController {


    private final MorningMonitoringAuditor graphAuditor;
    private final AthleteAuditor athleteAuditor;

    @Autowired
    public AthleteDashboardController(MorningMonitoringAuditor graphAuditor, AthleteAuditor athleteAuditor) {
        this.graphAuditor = graphAuditor;
        this.athleteAuditor = athleteAuditor;
    }

    /**
     * @return
     */
    @ModelAttribute("users")
    public List<Long> users() {
        return new ArrayList<Long>();
    }

    @GetMapping("athlete-dashboard/{id}")
    public String athleteDashboard(@PathVariable Long id, @ModelAttribute("users") List<Long> users, Model model) {
        try {
            if (users.get(users.size() - 1).equals(id)) {
                System.out.println("List of Users: " + users);
                model.addAttribute("userID", id);
                int[] wakingHR = {0, 0, 0, 0, 0, 0, 0};
                int[] standingHR = {0, 0, 0, 0, 0, 0, 0};
                int[] shape = {0, 0, 0, 0, 0, 0, 0};
                String[] dates = {"", "", "", "", "", "", ""};

                Athlete athlete = new Athlete();

                List<MorningMonitoring> fullList = graphAuditor.findAllMonitoringMonitoring();
                ArrayList<MorningMonitoring> relevantList = new ArrayList<>();

                //checks athlete in path is present and makes athlete object
                Optional<Athlete> optionalAthlete = athleteAuditor.findAthleteById(id);
                if (optionalAthlete.isPresent()) {
                    athlete = optionalAthlete.get();
                }

                //checks through the full list of morning monitoring entries and puts the ones for right athlete id into a different list
                for (MorningMonitoring entry : fullList) {
                    if (entry.getAthleteID().equals(athlete)) {
                        relevantList.add(entry);
                    }
                }

                //sets array values for the graph and checks that 7 days of records have been found.
                if (relevantList.size() >= 7) {
                    for (int i = 0; i < 7; i++) {
                        int j = relevantList.size() - 1;

                        shape[6 - i] = relevantList.get(j - i).getSleepQuality() +
                                relevantList.get(j - i).getSleepQuantityHours() +
                                relevantList.get(j - i).getPerceivedMentalState() +
                                relevantList.get(j - i).getPerceivedShape();

                        wakingHR[6 - i] = relevantList.get(j - i).getWalkingHeartRate();
                        standingHR[6 - i] = relevantList.get(j - i).getStandingHeartRate();
                        dates[6 - i] = relevantList.get(j - i).getDate().toString();
                    }
                }

                AthleteMMDGraphForm graphData = new AthleteMMDGraphForm(wakingHR, standingHR, shape, dates);
                model.addAttribute("graphs", graphData);

                return "athleteDashboard";
            } else {
                return "redirect:/athlete-dashboard/" + +users.get(users.size() - 1);
            }
        } catch (Exception e) {
            System.out.println();
            return "redirect:/404";
        }
    }
}
