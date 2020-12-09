package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AthletePreviousSportsForm {
    /**
     * The athleteID.
     */
    private Long athleteID;
    /**
     * The athletes previous sporting data.
     */
    @NotBlank(message = "Please enter if you have completed any previous sports, if not, enter 'No'")
    private String previousSport;
    /**
     * The number of months that an athlete has be training.
     */
    private String monthsTesting;
    /**
     * The number of sessions an athlete has done a week.
     */
    private String sessionsPerWeek;
    /**
     * The number of endurance sessions an athlete has done a week.
     */
    private String endurancePerWeek;
    /**
     * The number of strength sessions an athlete has done a week.
     */
    private String strengthPerWeek;
    /**
     * The number of years spent at that level.
     */
    private String yearsAtLevel;

    public AthletePreviousSportsForm(Long athleteID){
        this(athleteID, null, null, null, null, null, null);
    }
}
