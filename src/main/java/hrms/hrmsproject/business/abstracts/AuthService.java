package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterEmployerDto;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterJobSeekerDto;
import hrms.hrmsproject.entities.dtos.authDtos.UserLoginDto;

public interface AuthService {


    Result registerForEmployer(RegisterEmployerDto registerEmployerDto);
    Result registerForJobSeeker(RegisterJobSeekerDto registerJobSeekerDto);
    Result Login(UserLoginDto userLoginDto);
    Result UserExists(String email);

}
