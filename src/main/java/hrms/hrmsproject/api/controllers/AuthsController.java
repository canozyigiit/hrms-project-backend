//package hrms.hrmsproject.api.controllers;
//
//import hrms.hrmsproject.business.abstracts.AuthService;
//import hrms.hrmsproject.core.utilities.results.Result;
//import hrms.hrmsproject.entities.dtos.authDtos.RegisterEmployerRegisterDto;
//import hrms.hrmsproject.entities.dtos.authDtos.RegisterJobSeekerRegisterDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("api/auth")
//@CrossOrigin
//public class AuthsController {
//    private AuthService authService;
//
//    @Autowired
//    public AuthsController(AuthService authService) {
//        this.authService = authService;
//    }
//
////    @PostMapping("/login")
////    public Result login(@RequestBody UserLoginDto userLoginDto) {
////        return authService.Login(userLoginDto);
////    }
//
//    @PostMapping("/registerforemployer")
//    public Result registerForEmployer(@RequestBody RegisterEmployerRegisterDto registerForEmployerDto) {
//        return authService.registerForEmployer(registerForEmployerDto);
//    }
//
//    @PostMapping("/registerforjobseeker")
//    public Result registerForJobSeeker(@RequestBody RegisterJobSeekerRegisterDto registerForJobSeekerDto) {
//        return authService.registerForJobSeeker(registerForJobSeekerDto);
//    }
//}
