package hrms.hrmsproject.entities.dtos.authDtos;

import hrms.hrmsproject.core.entities.Dto;
import lombok.Data;

@Data
public class AuthDto implements Dto {
    private String email;
    private String password;
    private String passwordConfirm;
}
