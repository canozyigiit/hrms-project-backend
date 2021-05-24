package hrms.hrmsproject.entities.dtos.authDtos;


import hrms.hrmsproject.core.entities.Dto;

public class UserLoginDto implements Dto {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
