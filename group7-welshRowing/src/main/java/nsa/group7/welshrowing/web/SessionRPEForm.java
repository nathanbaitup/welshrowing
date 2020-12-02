package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionRPEForm {
    private Long athleteID;
    private Date dateOfSession;
    private String typeOfSession;
    private int rpe;
    private Integer sessionDurationMinutes;

    public SessionRPEForm(Long athleteID){
        this(athleteID, null, null, 0, null);
    }
}
