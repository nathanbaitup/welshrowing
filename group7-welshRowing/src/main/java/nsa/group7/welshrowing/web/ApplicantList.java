package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantList {
    /**
     * The athleteID from the database.
     */
    private Long athleteID;
    /**
     * The name of the applicant.
     */
    @NotBlank
    private String name;
    /**
     * The gender of the applicant.
     */
    @NotBlank
    private String gender;
    /**
     * The date of birth of the applicant.
     */
    @NotBlank
    private String dob;
    /**
     * A boolean that states if the athlete's application has been accepted.
     */
    @NotBlank
    private Boolean applicationStatus;
    /**
     * The applicant's mobile number.
     */
    private String mobileNumber;
    /**
     * The contact number for the applicant's guardian if under 18 years old.
     */
    private String guardianContactNumber;
}