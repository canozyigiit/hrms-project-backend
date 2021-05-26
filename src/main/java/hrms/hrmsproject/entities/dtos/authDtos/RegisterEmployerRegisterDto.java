package hrms.hrmsproject.entities.dtos.authDtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterEmployerRegisterDto extends AuthRegisterDto {

    private String companyName;
    private String website;
    private String phone;
    private String email;
    private String password;


}
