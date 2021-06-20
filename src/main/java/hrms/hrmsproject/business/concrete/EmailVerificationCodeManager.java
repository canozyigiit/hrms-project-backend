package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.EmailVerificationCodeService;
import hrms.hrmsproject.core.dataAccess.UserDao;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.EmailVerificationCodeDao;
import hrms.hrmsproject.entities.concretes.EmailVerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class EmailVerificationCodeManager implements EmailVerificationCodeService {
    private EmailVerificationCodeDao emailVerificationCodeDao;
    private UserDao userDao;

    @Autowired
    public EmailVerificationCodeManager(EmailVerificationCodeDao emailVerificationCodeDao,UserDao userDao) {
        this.emailVerificationCodeDao = emailVerificationCodeDao;
        this.userDao = userDao;
    }

    @Override
    public String createVerifyCode(User user) {
        String vCode = UUID.randomUUID().toString();
        LocalDate date = (LocalDate.now());
        EmailVerificationCode Code = new EmailVerificationCode();
        Code.setUserId(user.getId());
        Code.setCreatedDate(date);
        Code.setConfirmedDate(date);
        Code.setEmailVerificationCode(UUID.randomUUID().toString());
        this.emailVerificationCodeDao.save(Code);
        return vCode;
    }

    @Override
    public void sendMail(String mail) {

        System.out.println("Doğrulama Maili Gönderildi : " + mail);
    }

    @Override
    public Result add(EmailVerificationCode emailVerificationCode) {
        this.emailVerificationCodeDao.save(emailVerificationCode);
        return new SuccessResult();
    }

    @Override
    public DataResult<EmailVerificationCode> getByUserId(int userId) {
        return new SuccessDataResult<EmailVerificationCode>(this.emailVerificationCodeDao.getByUserId(userId));
    }


}
