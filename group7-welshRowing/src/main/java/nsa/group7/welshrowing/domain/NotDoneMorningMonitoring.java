package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class NotDoneMorningMonitoring {
    /**
     * the athleteID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athleteID;
    /**
     * the name of the athlete
     */
    private String name;

}
