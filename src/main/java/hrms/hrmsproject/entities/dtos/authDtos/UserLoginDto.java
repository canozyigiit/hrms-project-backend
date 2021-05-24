package hrms.hrmsproject.entities.dtos.authDtos;


import hrms.hrmsproject.core.entities.Dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class UserLoginDto implements Dto {
    private String email;
    private String password;



}
