package hrms.hrmsproject.entities.dtos.authDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class RegisterEmployerRegisterDto extends AuthRegisterDto {

    private String companyName;
    private String website;
    private String phone;
    private String password;


}
