package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SessionRPE")
public class SessionRPE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionRPEID;

    @ManyToOne
    @JoinTable(name = "athlete", joinColumns = @JoinColumn(name = "athleteID"))
    private Athlete athleteID;

    private Date date;
    private String typeOfCrossTraining;
    private int totalTimeMinutes;
    private String totalDistance;
}
