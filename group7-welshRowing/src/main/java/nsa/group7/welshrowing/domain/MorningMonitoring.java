package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MorningMonitoring")
public class MorningMonitoring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monitoringID")
    private Long morningMonitoringId;
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;

    private String date;

    private Integer walkingHeartRate;

    private Integer standingHeartRate;

    private Integer perceivedShape;

    private Integer perceivedMentalState;

    private Integer sleepQuantityHours;

    private Integer sleepQuality;

    public MorningMonitoring(Athlete a, String date, Integer walkingHeartRate, Integer standingHeartRate, Integer perceivedShape, Integer perceivedMentalState, Integer sleepQuantityHours, Integer sleepQuality) {
    }
}
