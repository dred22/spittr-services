package spittr.domain.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Reference {
    private Long id;
    @NotNull
    @Size(min = 5, max = 16, message = "{username.size}")
    private String userName;
    @NotNull
    @Size(min = 5, max = 25, message = "{password.size}")
    private String password;
    @NotNull
    @Size(min = 2, max = 30, message = "{firstname.size}")
    private String firstName;
    @NotNull
    @Size(min = 2, max = 30, message = "{lastname.size}")
    private String lastName;
    @NotNull
    @Email(message = "{email.valid}")
    private String email;

    private LocalDate ts;

}