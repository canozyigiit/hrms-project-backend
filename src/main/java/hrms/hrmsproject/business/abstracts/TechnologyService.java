package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Technology;
import hrms.hrmsproject.entities.dtos.technologyDtos.TechnologyDto;

import java.util.List;

public interface TechnologyService {

    Result add(TechnologyDto technologyDto);

    DataResult<List<TechnologyDto>> getAll();

    DataResult<Technology> getById(int id);
}
