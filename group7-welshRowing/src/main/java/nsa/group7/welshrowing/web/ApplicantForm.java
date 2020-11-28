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

    private Long userID;
    @NotBlank
    private String name;
    @NotBlank
    private String username;

    @Pattern(regexp = "^[a-zA-Z].*", message = "Must start with a letter")
    @Pattern(regexp = ".*[a-z].*", message = "Must contain lower-case")
    @Pattern(regexp = ".*[A-Z.].*", message = "Must contain upper-case")
    @Pattern(regexp = ".*[\\d].*", message = "Must contain a number")
    private String password;

    private String role;
}

