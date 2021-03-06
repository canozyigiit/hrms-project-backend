package hrms.hrmsproject.business.abstracts;


import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.SystemPersonnel;

import java.util.List;

public interface SystemPersonnelService {
    Result add(SystemPersonnel systemPersonnel);
    Result update(SystemPersonnel systemPersonnel);
    Result changeEmail(int id,String password,String email);


    DataResult<List<SystemPersonnel>> getAll();

    DataResult<SystemPersonnel> getById(int id);

    Result delete(SystemPersonnel systemPersonnel);

}
