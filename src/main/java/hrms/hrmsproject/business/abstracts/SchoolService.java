package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.School;
import hrms.hrmsproject.entities.dtos.schoolDtos.SchoolDto;

import java.time.LocalDate;
import java.util.List;

public interface SchoolService {

    Result add(SchoolDto schoolDto);

    DataResult<List<SchoolDto>> getAll();

    Result delete(int id);
    Result update(int id,String schoolDepartment, String schoolName, LocalDate startedDate,LocalDate endedDate,
                  Integer graduateId);

    DataResult<School> getById(int id);
    DataResult<List<SchoolDto>> findAllByResumeIdOrderByEndedDateDesc(int id);
}
