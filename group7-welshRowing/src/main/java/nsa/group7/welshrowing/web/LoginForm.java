package nsa.group7.welshrowing.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {
    //username validation
    @NotBlank(message = "Please check your username is correct")
    private String username;

    //password validation
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z].*",message="Must start with a letter")
    @Pattern(regexp = "^.*[a-z].*",message="Must contain lowercase")
    @Pattern(regexp = "^.*[A-Z].*",message="Must contain upper-case")
    @Pattern(regexp = "^.*[\\d].*",message="Must contain a number")
    private String password;




}
