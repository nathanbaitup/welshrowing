package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MorningMonitoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long monitoringID;
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;

    private Date date;

    private int walkingHeartRate;

    private int standingHeartRate;

    private int perceivedShape;

    private int perceivedMentalState;

    private int sleepQuantityHours;

    private int sleepQuality;

}
