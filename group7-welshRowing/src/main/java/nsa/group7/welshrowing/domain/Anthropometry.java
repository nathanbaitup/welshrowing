package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "MedicalData")
public class Anthropometry {
    /**
     * The medicalDataID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalDataID;
    /**
     * The athleteID.
     */
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    /**
     * Any injuries that can affect an athletes performance.
     */
    private String injuries;
    /**
     * The height of the athlete in centimeters.
     */
    private Integer heightCM;
    /**
     * The weight of the athlete in kilograms.
     */
    private Integer weightKG;
    /**
     * The arm span of the athlete in centimeters.
     */
    private Integer armspanCM;

}
