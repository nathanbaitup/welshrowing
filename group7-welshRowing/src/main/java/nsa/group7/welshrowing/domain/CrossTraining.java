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
public class CrossTraining {
    /**
     * The crossTraining ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long crossTrainingId;
    /**
     * Foriegn key athleteID being brought over linking the two tables together.
     */
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    /**
     * Date of cross training session.
     */
    private String dateOfSession;
    /**
     * The type of Cross Training
     */
    private String typeOfCrossTraining;
    /**
     * The total amount of time in minutes for training.
     */
    private Integer totalTimeMinutes;
    /**
     * The total distance the athlete has done for cross training.
     */
    private Integer totalDistance;
}
