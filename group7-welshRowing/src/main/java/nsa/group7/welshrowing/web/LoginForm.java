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
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,64}$", message = "Password must contain:" +
            "\nBetween 8 and 64 characters,\nA Capital Letter,\nA Lowercase Letter," +
            "\nA number,\nA Special Character(# ? ! @ $ % ^ & * -)")
    @NotBlank
    private String password;




}
