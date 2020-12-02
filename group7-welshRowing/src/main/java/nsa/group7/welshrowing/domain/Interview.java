package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewID;
    /**
     *
     */
    private String answer1;
    /**
     *
     */
    private String answer2;
    /**
     *
     */
    private String answer3;
    /**
     *
     */
    private String answer4;
    /**
     *
     */
    private String answer5;
    /**
     *
     */
    private String answer6;
    /**
     *
     */
    private Integer answer7;
    /**
     *
     */
    private Integer answer8;
    /**
     *
     */
    private Integer answer9;
    /**
     *
     */
    private Integer answer10;
    /**
     *
     */
    private String answer11;
    /**
     *
     */
    private String answer12;
    /**
     *
     */
    private String answer13;
    /**
     *
     */
    private Integer answer14;
    /**
     *
     */
    private Integer answer15;
    /**
     *
     */
    private Integer answer16;
    /**
     *
     */
    private Integer answer17;
    /**
     *
     */
    private Integer answer18;
    /**
     *
     */
    private Integer answer19;
    /**
     *
     */
    private Integer answer20;
    /**
     *
     */
    private Integer answer21;
    /**
     *
     */
    private Integer answer22;
    /**
     *
     */
    private Integer answer23;
    /**
     *
     */
    private Integer answer24;
}
