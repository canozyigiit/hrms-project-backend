package hrms.hrmsproject.entities.dtos.authDtos;

import hrms.hrmsproject.core.entities.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
public class RegisterEmployerDto extends AuthDto {

    private String companyName;
    private String website;
    private String phone;
    private String email;
    private String password;


}
