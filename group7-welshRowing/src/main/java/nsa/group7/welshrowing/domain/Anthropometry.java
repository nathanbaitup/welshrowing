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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long medicalDataID;
    @ManyToOne
    @JoinColumn(name = "athleteID")
    private Athlete athleteID;
    private String injuries;
    private Integer heightCM;
    private Integer weightKG;
    private Integer armspanCM;

    public Anthropometry(Athlete athleteID){
        this(null, athleteID, null, null, null, null);
    }

}
