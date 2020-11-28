package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantForm {
    /**
     * The ID that will be assigned to a user.
     */
    private Long userID;
    /**
     * The name of the user.
     */
    @NotBlank(message = "Please enter your full name")
    private String name;
    /**
     * The username of the user.
     */
    @NotBlank(message = "Please enter a username")
    private String username;
    /**
     * The users password, checks password contains lower-case, uppercase and numerical values.
     */
    @Pattern(regexp = "^[a-zA-Z].*",message = "Must start with a letter")
    @Pattern(regexp = ".*[a-z].*", message = "Must contain lower-case")
    @Pattern(regexp = ".*[A-Z.].*", message = "Must contain upper-case")
    @Pattern(regexp = ".*[\\d].*", message = "Must contain a number")
    private String password;

    private String role;
}

