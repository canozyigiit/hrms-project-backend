package hrms.hrmsproject.entities.dtos.authDtos;


import hrms.hrmsproject.core.entities.Dto;

public class UserRegisterDto implements Dto {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

}
