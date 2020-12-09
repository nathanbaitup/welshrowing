package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsa.group7.welshrowing.domain.Athlete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AnthropometryForm {
    /**
     * The athleteID.
     */
    @NotBlank
    private Long athleteID;
    /**
     * Any injuries that can affect an athletes performance.
     */
    @NotBlank(message = "Please enter any injuries, if none, enter 'none'.")
    private String injuries;
    /**
     * The height of the athlete in centimeters.
     */
    @NotBlank(message = "Please enter the athletes height in CM.")
    private Integer heightCM;
    /**
     * The weight of the athlete in kilograms.
     */
    @NotBlank(message = "Please enter the athletes weight in KG.")
    private Integer weightKG;
    /**
     * The arm span of the athlete in centimeters.
     */
    @NotBlank(message = "Please enter the athletes armspan in CM.")
    private Integer armspanCM;

    public AnthropometryForm(Long athleteID){
        this(athleteID, null, null, null, null);
    }

}
