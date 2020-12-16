package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AthleteMMDGraphForm {
    //int array for the 7 day waking heart rates
    private int[] wakingHR;

    //int array for the 7 day waking standing rates
    private int[] standingHR;

    //int array for the 7 day shape readings
    private int[] shape;

    //string array of dates
    private String[] dates;
}
