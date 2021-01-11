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
    @NotBlank(message = "Please enter your full name.")
    private String name;
    /**
     * The username of the user.
     */
    @NotBlank(message = "Please enter a username.")
    @Pattern(regexp = "^[a-zA-Z]\\w{3,20}$", message = "Please enter between 3 and 20 characters. Letters only.")
    private String username;
    /**
     * The users password, checks password contains lower-case, uppercase and numerical values.
     */
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,64}$", message = "Password must contain:" +
            "\nBetween 8 and 64 characters,\nA Capital Letter,\nA Lowercase Letter," +
            "\nA number,\nA Special Character(# ? ! @ $ % ^ & * -)")
    @NotBlank
    private String password;

    private String role;
}

