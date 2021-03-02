package nsa.group7.welshrowing.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "User")
public class Applicant {
    /**
     * The ID that will be assigned to a user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The username of the user.
     */
    private String username;
    /**
     * The users password, checks password contains lower-case, uppercase and numerical values.
     */
    private String password;
    /**
     * The role of the user. Will automatically be assumed as 'applicant'
     */
    private String role;
}
