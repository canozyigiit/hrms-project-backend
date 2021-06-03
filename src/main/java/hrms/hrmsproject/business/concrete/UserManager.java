package hrms.hrmsproject.business.concrete;


import hrms.hrmsproject.business.abstracts.UserService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.core.dataAccess.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager implements UserService {
    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Result add(User user) {
        this.userDao.save(user);
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
    public Result delete(User user) {
        this.userDao.delete(user);
        return new SuccessResult(Messages.userDeleted);
    }
}
