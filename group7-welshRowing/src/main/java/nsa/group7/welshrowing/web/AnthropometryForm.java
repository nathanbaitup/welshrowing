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
    private Long athleteID;
    /**
     * Any injuries that can affect an athletes performance.
     */
    @NotBlank(message = "Please enter any injuries, if none, enter 'none'.")
    private String injuries;
    /**
     * The height of the athlete in centimeters.
     */
    private Integer heightCM;
    /**
     * The weight of the athlete in kilograms.
     */
    private Integer weightKG;
    /**
     * The arm span of the athlete in centimeters.
     */
    private Integer armspanCM;

    public AnthropometryForm(Long athleteID){
        this(athleteID, null, null, null, null);
    }

}
