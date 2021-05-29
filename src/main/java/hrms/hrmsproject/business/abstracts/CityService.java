package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.City;

import java.util.List;

public interface CityService {
    Result add(City city);
    DataResult<List<City>> getAll();
}
