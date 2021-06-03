package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.School;

import java.util.List;

public interface SchoolService {

    Result add(School school);

    DataResult<List<School>> getAll();

    DataResult<School> getById(int id);
}
