package hrms.hrmsproject.entities.dtos.authDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class RegisterJobSeekerRegisterDto extends AuthRegisterDto {
    private String firstName;
    private String lastName;
    private String nationalId;
    private Date dateOfBirth;
    private String email;
    private String password;

}
