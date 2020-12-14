package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "AthletePreviousSports")
public class AthletePreviousSports {
    /**
     * The athlete previous sports ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apsID;
    /**
     * The athleteID.
     */
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    /**
     * The athletes previous sporting data.
     */
    private String previousSport;
    /**
     * The number of months that an athlete has be training.
     */
    private String monthsTesting;
    /**
     * The number of sessions an athlete has done a week.
     */
    private String sessionsPerWeek;
    /**
     * The number of endurance sessions an athlete has done a week.
     */
    private String endurancePerWeek;
    /**
     * The number of strength sessions an athlete has done a week.
     */
    private String strengthPerWeek;
    /**
     * The number of years spent at that level.
     */
    private String yearsAtLevel;
}
