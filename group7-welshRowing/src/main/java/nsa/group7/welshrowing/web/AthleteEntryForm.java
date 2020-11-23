package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AthleteEntryForm {
    /**
     * The name of the athlete.
     */
    private String name;
    /**
     * The gender of the athlete. Will be radio button options on form.
     */
    private String gender;
    /**
     * The date of birth of the athlete.
     */
    private Date dob;
    /**
     * The athlete's personal email address.
     */
    private String email;
    /**
     * The athlete's password to log into the system.
     */
    private String password;
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
    private String homeAddress;
    /**
     * The athlete's university address if applicable.
     */
    private String uniAddress;
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
}
