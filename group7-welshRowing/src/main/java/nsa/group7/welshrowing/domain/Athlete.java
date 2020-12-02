package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Athlete {
    /**
     * The athleteID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long athleteID;
    /**
     * The name of the athlete.
     */
    private Long coachID;
    private String name;
    /**
     * The gender of the athlete. Will be radio button options on form.
     */
    private String gender;
    /**
     * The date of birth of the athlete.
     */
    private String dob;
    /**
     * A boolean that states if the athlete is applying to the programme (true) or has been accepted (false).
     */
    private Boolean applicationStatus;
    /**
    /**
     * The athlete's personal email address.
     */
    private String email;
    /**
     * The athlete's mobile number.
     */
    private String mobileNumber;
    /**
     * The athlete's home telephone number.
     */
    private String telephoneNumber;
    /**
     * The athlete's home address.
     */
    private String address;
    /**
     * The postcode where the athlete is living.
     */
    private String postcode;
    /**
     * The name of the school or university the athlete is currently in.
     */
    private String placeOfEducation;
    /**
     * The name of the parent or guardian of the athlete, if they are under 18 years of age.
     */
    private String guardianName;
    /**
     * The relationship of the parent or guardian of the athlete, if they are under 18 years of age.
     */
    private String relationshipToAthlete;
    /**
     * The phone number of the parent or guardian of the athlete, if they are under 18 years of age.
     */
    private String guardianContactNumber;
    /**
     * The email of the parent or guardian of the athlete, if they are under 18 years of age.
     */
    private String guardianEmail;
    /**
     * A short string about where the athlete heard about Rowing Wales.
     * Will be a group of radio buttons with an optional text box if an athlete has heard about Rowing Wales from another place.
     */
    private String heardFrom;
    /**
     * A boolean that states if a letter of interest has been sent to the athlete.
     */
    private Boolean interestLetter;
    /**
     * A string that states the result after the interview process of an athlete.
     */
    private String postTestResult;
}