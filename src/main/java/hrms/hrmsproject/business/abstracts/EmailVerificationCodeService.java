package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.EmailVerificationCode;

public interface EmailVerificationCodeService {

    Result add(EmailVerificationCode emailVerificationCode);
}
