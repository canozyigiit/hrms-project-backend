package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobAdvert;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;

public interface JobAdvertService {
    Result add(JobAdvert jobAdvert);
    Result update(JobAdvert jobAdvert);
    Result delete(int id);
    Result changeOpenToClose(int id);
    DataResult<JobAdvert> getById(int id);
    DataResult<List<JobAdvert>> getAll();

    DataResult<List<JobAdvert>>getByisOpenTrueOrderByCreatedDateDesc();
    DataResult<List<JobAdvert>> getAllOpenJobAdvertList();
    DataResult<List<JobAdvert>> getByisOpenTrueAndEmployer_Id(int id);
//    DataResult<List<JobAdvert>> getAllOpenAndDeadLine(boolean isOpen, LocalDate deadline);
}
