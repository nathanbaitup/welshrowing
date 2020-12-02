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
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;

    private String dateOfSession;

    private String typeOfSession;
    @Column(name = "RPE")
    private int rpe;

    private int sessionDurationMinutes;
}
