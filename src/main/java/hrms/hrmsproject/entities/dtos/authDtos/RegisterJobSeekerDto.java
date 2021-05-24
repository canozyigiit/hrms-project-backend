package hrms.hrmsproject.entities.dtos.authDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegisterJobSeekerDto {
    private String firstName;
    private String lastName;
    private String nationalityId;
    private Date dateOfBirth;
    private String email;
    private String password;

}
