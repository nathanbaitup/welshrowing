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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apsID;
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    private String previousSports;
    private String monthsTesting;
    private String sessionsPerWeek;
    private String endurancePerWeek;
    private String strengthPerWeek;
    private String yearsAtLevel;
}
