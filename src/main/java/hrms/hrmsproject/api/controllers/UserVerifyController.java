package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.UserVerifyService;
import hrms.hrmsproject.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/userverify")
@CrossOrigin
public class UserVerifyController {

    private UserVerifyService userVerifyService;

    @Autowired
    public UserVerifyController(UserVerifyService userVerifyService) {
        this.userVerifyService = userVerifyService;
    }

    @PostMapping("verifyuser")
    public Result verifyUser(String userVerifyCode, int userId){
       return this.userVerifyService.verifyUser(userVerifyCode,userId);
    }

}
