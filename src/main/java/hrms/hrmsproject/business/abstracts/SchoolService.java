package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.School;
import hrms.hrmsproject.entities.dtos.schoolDtos.SchoolDto;

import java.util.List;

public interface SchoolService {

    Result add(SchoolDto schoolDto);

    DataResult<List<SchoolDto>> getAll();

    Result delete(int id);
    Result update(SchoolDto schoolDto);

    DataResult<School> getById(int id);
    DataResult<List<SchoolDto>> findAllByResumeIdOrderByEndedDateDesc(int id);
}
