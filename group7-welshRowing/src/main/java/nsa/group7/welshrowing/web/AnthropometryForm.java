package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsa.group7.welshrowing.domain.Athlete;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AnthropometryForm {
    private Long athleteID;
    private String injuries;
    private Integer heightCM;
    private Integer weightKG;
    private Integer armspanCM;

    public AnthropometryForm(Long athleteID){
        this(athleteID, null, null, null, null);
    }

}
