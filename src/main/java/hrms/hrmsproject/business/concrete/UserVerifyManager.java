package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.EmailVerificationCodeService;
import hrms.hrmsproject.business.abstracts.UserService;
import hrms.hrmsproject.business.abstracts.UserVerifyService;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.ErrorResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.entities.concretes.EmailVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserVerifyManager implements UserVerifyService {

    EmailVerificationCodeService emailVerificationCodeService;
    UserService userService;

    @Autowired
    public UserVerifyManager(EmailVerificationCodeService emailVerificationCodeService, UserService userService) {
        this.emailVerificationCodeService = emailVerificationCodeService;
        this.userService = userService;
    }

    @Override
    public Result verifyUser(String userVerifyCode, int userId) {
        EmailVerificationCode emailVerificationCode = this.emailVerificationCodeService.getByUserId(userId).getData();
        User user = this.userService.getById(userId).getData();

        if (!emailVerificationCode.getEmailVerificationCode().equals(userVerifyCode)){
            return new ErrorResult("The code you entered is incorrect");
        }
        else if (emailVerificationCode.isConfirmed()){
            return new ErrorResult("The entered code is already confirmed");
        }

        emailVerificationCode.setConfirmed(true);
        user.setVerify(true);
        LocalDate date = LocalDate.now();
        emailVerificationCode.setConfirmedDate(date);
        this.userService.add(user);
        this.emailVerificationCodeService.add(emailVerificationCode);
        return new SuccessResult("Verification successful");
    }
}
