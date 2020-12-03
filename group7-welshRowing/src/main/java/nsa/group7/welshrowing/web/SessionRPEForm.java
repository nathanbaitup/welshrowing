package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionRPEForm {
    /**
     *The athleteID
     */
    @NotBlank
    private Long athleteID;
    /**
     *The date of the session.
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfSession;
    /**
     * The type of session.
     */
    @NotBlank
    private String typeOfSession;
    /**
     * The rate of perceived exertion.
     */
    @NotBlank
    private int rpe;
    /**
     * The duration of the session stored in minutes.
     */
    @NotBlank
    @Pattern(regexp = "[0-9]+", message = "Please only enter numbers.")
    private Integer sessionDurationMinutes;

    /**
     * Method that only takes the athleteID to ensure the form saves the data for the specific athlete.
     *
     * @param athleteID - the athleteID taken from the current active athlete.
     */
    public SessionRPEForm(Long athleteID){
        this(athleteID, null, null, 0, null);
    }
}
