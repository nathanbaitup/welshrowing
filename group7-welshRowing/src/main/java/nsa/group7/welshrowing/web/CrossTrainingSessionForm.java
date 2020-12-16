package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CrossTrainingSessionForm {
    /**
     * athleteID in database.
     */
    @NotBlank
    private Long athleteID;
    /**
     * Date of crossTraining session.
     */
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfSession;
    /**
     * Type of crossTraining session.
     */
    @NotBlank
    private String typeOfCrossTraining;
    /**
     * Total amount of time spent of CrossTraining.
     */
    @NotBlank
    @NumberFormat
    private Integer totalTimeMinutes;
    /**
     * Total distance in CrossTraining.
     */
    @NotBlank
    private String totalDistance;

    /**
     * Only takes Athlete as a parameter to make sure that it is saved to the correct Athlete within the database.
     * @param athleteID
     */

    public CrossTrainingSessionForm(Long athleteID){
        this(athleteID, null, null, 0, null);
    }
}
