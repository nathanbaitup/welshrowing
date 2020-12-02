package nsa.group7.welshrowing.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "CrossTraining")
public class crossTraining {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crossTrainingId;

    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;

    private String dateOfSession;

    private String typeOfCrossTraining;

    private Integer totalTimeMinutes;

    private Integer totalDistance;
}
