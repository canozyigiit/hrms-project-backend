package hrms.hrmsproject.business.required;

import hrms.hrmsproject.business.abstracts.EmailVerificationCodeService;
import hrms.hrmsproject.business.abstracts.ValidateService;
import hrms.hrmsproject.core.dataAccess.UserDao;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobSeekerValidateManager implements ValidateService<JobSeeker> {


    private UserDao userDao;
    private EmailVerificationCodeService emailVerificationCodeService;

    @Autowired
    public JobSeekerValidateManager(UserDao userDao, EmailVerificationCodeService emailVerificationCodeService) {
        super();
        this.userDao = userDao;
        this.emailVerificationCodeService = emailVerificationCodeService;
    }


    @Override
    public Result verifyData(JobSeeker jobSeeker) {
        this.emailVerificationCodeService.createVerifyCode(userDao.getOne(jobSeeker.getId()));
        this.emailVerificationCodeService.sendMail(jobSeeker.getEmail());
        return new SuccessResult();
    }
}
