package hrms.hrmsproject.business.abstracts;


import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;

import java.util.List;

public interface UserService {
    Result add(User user);

    DataResult<List<User>> getAll();

    DataResult<User> getById(int id);

    DataResult<User> getByEmail(String email);

    Result delete(User user);


}
