package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Technology;

import java.util.List;

public interface TechnologyService {

    Result add(Technology technology);

    DataResult<List<Technology>> getAll();

    DataResult<Technology> getById(int id);
}
