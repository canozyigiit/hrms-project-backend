package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.authDtos.UserLoginDto;
import hrms.hrmsproject.entities.dtos.authDtos.UserRegisterDto;

public interface AuthService {


    User Register(UserRegisterDto userRegisterDto);
    User Login(UserLoginDto userLoginDto);
    Result UserExists(String email);

}
