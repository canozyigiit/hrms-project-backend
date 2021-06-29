package hrms.hrmsproject.business.abstracts;


import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EmployerService {
    Result add(Employer employer);

    Result update(Employer employer);
    DataResult<List<Employer>> getByisConfirmedFalse();

    Result changeEmail(int id, String email, String password);

    DataResult<List<Employer>> getAll();

    DataResult<Employer> getById(int id);

    Result delete(Employer employer);

    DataResult<List<Employer>> getByisUpdatedTrue();

    Result addImageEmployer(MultipartFile file, int employerId);

}
