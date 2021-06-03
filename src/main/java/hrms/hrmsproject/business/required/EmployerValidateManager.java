package hrms.hrmsproject.business.required;
import hrms.hrmsproject.business.abstracts.EmailVerificationCodeService;
import hrms.hrmsproject.business.abstracts.ValidateService;
import hrms.hrmsproject.core.dataAccess.UserDao;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployerValidateManager implements ValidateService<Employer> {


    private UserDao userDao;
    private EmailVerificationCodeService emailVerificationCodeService;

    @Autowired
    public EmployerValidateManager(UserDao userDao, EmailVerificationCodeService emailVerificationCodeService) {
        super();
        this.userDao = userDao;
        this.emailVerificationCodeService = emailVerificationCodeService;
    }


    @Override
    public Result verifyData(Employer employer) {
        this.emailVerificationCodeService.createVerifyCode(userDao.getOne(employer.getId()));
        this.emailVerificationCodeService.sendMail(employer.getEmail());
        return new SuccessResult();
    }
}
