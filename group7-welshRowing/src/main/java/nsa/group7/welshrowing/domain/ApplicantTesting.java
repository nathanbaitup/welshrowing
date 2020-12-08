package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsa.group7.welshrowing.domain.Athlete;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "AthleteTest")
public class ApplicantTesting {
    /**
     * The test ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athleteTestID;
    /**
     * The ID of the athlete completing the test.
     */
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    /**
     * The date of the test.
     */
    @NotBlank(message = "Please enter the date of the test.")
    private String dateOfTest;
    /**
     * Any comments the coach wants to make about the athlete.
     */
    private String athleteComments;
    /**
     * Athletes legPress3Reps results.
     */
    @NotBlank(message = "Please enter the result.")
    private int legPress3Reps;
    /**
     * Athletes armPress3Reps results.
     */
    @NotBlank(message = "Please enter the result.")
    private int armPress3Reps;
    /**
     * Athletes armPull3Reps results.
     */
    @NotBlank(message = "Please enter the result.")
    private int armPull3Reps;
    /**
     * Athletes armPull3Reps results.
     */
    @NotBlank(message = "Please enter the result.")
    private int armPull15Reps;
    /**
     * Athletes score on Schwinn.
     */
    @NotBlank(message = "Please enter the result.")
    private int score;
    /**
     * Coaches observations under Schwinn.
     */
    private String observations;
    /**
     * The basic core of the athlete, marked as green, amber or red.
     */
    @NotBlank(message = "Please select an option.")
    private String basicCore;
    /**
     * Any notes the coach had on the athletes basic core.
     */
    private String bcNotes;
    /**
     * The flexibility of the athlete, marked as green, amber or red.
     */
    @NotBlank(message = "Please select an option.")
    private String flexibility;
    /**
     * Any notes the coach had on the athletes flexibility.
     */
    private String fNotes;
}
