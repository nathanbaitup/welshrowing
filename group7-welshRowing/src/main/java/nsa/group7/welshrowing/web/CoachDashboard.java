package nsa.group7.welshrowing.web;

import lombok.Value;

@Value
public class CoachDashboard {
    /**
     * Thymleaf Coach Name and Coach ID being passed in for the dashboard.
     */
    private String name;
    private String text;
}
