package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Graduate;

import java.util.List;

public interface GraduateService {

    Result add(Graduate graduate);

    DataResult<List<Graduate>> getAll();

    DataResult<Graduate> getById(int id);
}
