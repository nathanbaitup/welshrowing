package nsa.group7.welshrowing.web;

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
public class ApplicantTestingForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athleteTestID;
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    @NotBlank
    private String dateOfTest;
    @NotBlank
    private String athleteComments;
    private int legPress3Reps;
    private int armPress3Reps;
    private int armPull3Reps;
    private int armPull15Reps;
    private int score;
    private String observations;
}
