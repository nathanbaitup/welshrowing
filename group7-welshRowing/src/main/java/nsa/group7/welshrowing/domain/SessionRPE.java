package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SessionRPE")
public class SessionRPE {
    /**
     * The session ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionRPEID;
    /**
     * The athleteID foreign key that is linked to the athletes table.
     */
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    /**
     * The date of the session.
     */
    private String dateOfSession;
    /**
     * The type of session.
     */
    private String typeOfSession;
    /**
     * The rate of perceived exertion of an athlete. 1 - 10 rating.
     */
    @Column(name = "RPE")
    private int rpe;
    /**
     * The duration of the session, stored in minutes.
     */
    private int sessionDurationMinutes;
}
