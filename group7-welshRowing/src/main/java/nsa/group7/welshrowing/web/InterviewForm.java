package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nsa.group7.welshrowing.domain.Athlete;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewForm {

    private Long athleteID;


    @NotBlank
    private String answer1;
    /**
     *
     */
    @NotBlank
    private String answer2;
    /**
     *
     */
    @NotBlank
    private String answer3;
    /**
     *
     */
    @NotBlank
    private String answer4;
    /**
     *
     */
    @NotBlank
    private String answer5;
    /**
     *
     */
    @NotBlank
    private String answer6;
    /**
     *
     */
    @NotBlank
    private Integer answer7;
    /**
     *
     */
    @NotBlank
    private Integer answer8;
    /**
     *
     */
    @NotBlank
    private Integer answer9;
    /**
     *
     */
    @NotBlank
    private Integer answer10;
    /**
     *
     */
    @NotBlank
    private String answer11;
    /**
     *
     */
    @NotBlank
    private String answer12;
    /**
     *
     */
    @NotBlank
    private String answer13;
    /**
     *
     */
    @NotBlank
    private Integer answer14;
    /**
     *
     */
    @NotBlank
    private Integer answer15;
    /**
     *
     */
    @NotBlank
    private Integer answer16;
    /**
     *
     */
    @NotBlank
    private Integer answer17;
    /**
     *
     */
    @NotBlank
    private Integer answer18;
    /**
     *
     */
    @NotBlank
    private Integer answer19;
    /**
     *
     */
    @NotBlank
    private Integer answer20;
    /**
     *
     */
    @NotBlank
    private Integer answer21;
    /**
     *
     */
    @NotBlank
    private Integer answer22;
    /**
     *
     */
    @NotBlank
    private Integer answer23;
    /**
     *
     */
    @NotBlank
    private Integer answer24;
}