package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.AuthService;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterEmployerDto;
import hrms.hrmsproject.entities.dtos.authDtos.RegisterJobSeekerDto;
import hrms.hrmsproject.entities.dtos.authDtos.UserLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthControllers {
    private AuthService authService;

    @Autowired
    public AuthControllers(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDto userLoginDto) {
        return authService.Login(userLoginDto);
    }

    @PostMapping("/registerforemployer")
    public Result registerForEmployer(@RequestBody RegisterEmployerDto registerForEmployerDto) {
        return authService.registerForEmployer(registerForEmployerDto);
    }

    @PostMapping("/registerforjobseeker")
    public Result registerForJobSeeker(@RequestBody RegisterJobSeekerDto registerForJobSeekerDto) {
        return authService.registerForJobSeeker(registerForJobSeekerDto);
    }
}
