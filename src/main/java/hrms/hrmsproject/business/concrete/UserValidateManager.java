package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.EmailVerificationCodeService;
import hrms.hrmsproject.business.abstracts.UserService;
import hrms.hrmsproject.business.abstracts.ValidateService;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserValidateManager implements ValidateService {
    private UserService userService;
    private EmailVerificationCodeService emailVerificationCodeService;

    @Autowired
    public UserValidateManager(UserService userService, EmailVerificationCodeService emailVerificationCodeService) {
        this.userService = userService;
        this.emailVerificationCodeService = emailVerificationCodeService;
    }

    @Override
    public Result verifyData(int userId) {
         User user = this.userService.getById(userId).getData();
        this.emailVerificationCodeService.createVerifyCode(user);
        this.emailVerificationCodeService.sendMail(user.getEmail());
        return new SuccessResult("Doğrulama kodu gönderildi");
    }

}
