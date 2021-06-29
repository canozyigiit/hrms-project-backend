package hrms.hrmsproject.business.concrete;


import hrms.hrmsproject.business.abstracts.UserService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.dataAccess.UserDao;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;
    // private ValidateService validateService;

    @Autowired
    public UserManager(UserDao userDao
                       // ValidateService validateService
    ) {
        this.userDao = userDao;
        // this.validateService = validateService;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
        //validateService.verifyData(user.getId());
        return new SuccessResult(Messages.userAdded);
    }

    @Override
    public DataResult<List<User>> getAll() {
        return new SuccessDataResult<List<User>>(this.userDao.findAll(), Messages.userGetAll);
    }

    @Override
    public DataResult<User> getById(int id) {

        return new SuccessDataResult<User>(this.userDao.findById(id).orElse(null), Messages.userGet);
    }

    @Override
    public DataResult<User> getByEmail(String email) {
        return new SuccessDataResult<User>(userDao.findByEmail(email));
    }

    @Override
    public Result changePassword(int id, String password, String newPassword) {
        User user = this.userDao.findById(id).orElse(null);
        if (user == null) {
            return new ErrorResult("Kullanıcı bulunamadı");
        }
        var result = BusinessRules.Run(passwordCheck(id,password),
                newPasswordCheck(user,newPassword));
        if (result != null) {
            return result;
        }
        user.setPassword(newPassword.trim());
        this.userDao.save(user);
        return new SuccessResult("Şifre güncellendi");
    }

    @Override
    public Result changeEmail(User user) {
        User u = this.userDao.findById(user.getId()).orElse(null);
        if (u == null) {
            return new ErrorResult("Kullanıcı bulunamadı");
        }
        var result = BusinessRules.Run(passwordCheck(user.getId(), user.getPassword()));
        if (result != null) {
            return result;
        }
        u.setEmail(user.getEmail());
        userDao.save(u);
        return new SuccessResult("Email Güncellendi");

    }

    @Override
    public Result delete(User user) {
        this.userDao.delete(user);
        return new SuccessResult(Messages.userDeleted);
    }

//    private Result checkEmptyUser(User user){
//        User u = this.userDao.findById(user.getId()).orElse(null);
//        if (u == null) {
//            return new ErrorResult("Kullanıcı bulunamadı");
//        }
//        return new SuccessResult();
//    }
    private Result passwordCheck(int id,String password){
        User user = this.userDao.findById(id).orElse(null);
        if (!user.getPassword().equals(password)) {
            return new ErrorResult("Şifre Yanlış");
        }
        return new SuccessResult();
    }

    private Result newPasswordCheck(User user,String newPassword){
        User u = this.userDao.findById(user.getId()).orElse(null);
        if (u.getPassword().equals(newPassword)) {
            return new ErrorResult("Yeni Şifre, Eski Şifre İle Aynı Olamaz");
        }
        if (newPassword.length() <= 4) {
            return new ErrorResult("Şifre En Az 5 Krakterli Olmalıdır");
        }
        return new SuccessResult();
    }

}
