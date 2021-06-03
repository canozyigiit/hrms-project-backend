package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.Result;

public interface EmailVerificationCodeService {

    String createVerifyCode(User user);
    void sendMail(String mail);
    Result verifyUser(String code);
}
