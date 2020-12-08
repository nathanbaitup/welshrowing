package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AthletePreviousSportsForm {
    private Long athleteID;
    private String previousSports;
    private String monthsTesting;
    private String sessionsPerWeek;
    private String endurancePerWeek;
    private String strengthPerWeek;
    private String yearsAtLevel;

    public AthletePreviousSportsForm(Long athleteID){
        this(athleteID, null, null, null, null, null, null);
    }
}
