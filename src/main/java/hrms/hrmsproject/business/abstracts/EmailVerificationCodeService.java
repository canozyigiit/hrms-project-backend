package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.EmailVerificationCode;

public interface EmailVerificationCodeService {

    String createVerifyCode(User user);
    void sendMail(String mail);

    Result add(EmailVerificationCode emailVerificationCode);

    DataResult<EmailVerificationCode> getByUserId(int userId);
}
