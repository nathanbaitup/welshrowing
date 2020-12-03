package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MorningMonitoringList {

    private Long athleteID;

    private String date;

    private Integer walkingHeartRate;

    private Integer standingHeartRate;

    private Integer perceivedShape;

    private Integer perceivedMentalState;

    private Integer sleepQuantityHours;

    private Integer sleepQuality;
}
