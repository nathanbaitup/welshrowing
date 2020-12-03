package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MorningMonitoringForm {

    private Long athleteID;

    private Date date;

    private int walkingHeartRate;

    private int standingHeartRate;

    private int perceivedShape;

    private int perceivedMentalState;

    private int sleepQuantityHours;

    private int sleepQuality;
}
