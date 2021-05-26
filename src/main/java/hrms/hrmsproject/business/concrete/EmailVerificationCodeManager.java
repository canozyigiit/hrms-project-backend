package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.EmailVerificationCodeService;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.EmailVerificationCodeDao;
import hrms.hrmsproject.entities.concretes.EmailVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailVerificationCodeManager implements EmailVerificationCodeService {
    private EmailVerificationCodeDao emailVerificationCodeDao;

    @Autowired
    public EmailVerificationCodeManager(EmailVerificationCodeDao emailVerificationCodeDao) {
        this.emailVerificationCodeDao = emailVerificationCodeDao;
    }

    @Override
    public Result add(EmailVerificationCode emailVerificationCode) {
        this.emailVerificationCodeDao.save(emailVerificationCode);
        return new SuccessResult();

    }
}
