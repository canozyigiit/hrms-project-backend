package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobSeekerFavoriteJobAdvert;

import java.util.List;

public interface JobSeekerFavoriteJobAdvertService {
    DataResult<List<JobSeekerFavoriteJobAdvert>> getAll();
    Result add(JobSeekerFavoriteJobAdvert jobSeekerFavoriteJobAdvert);

    Result delete(JobSeekerFavoriteJobAdvert jobSeekerFavoriteJobAdvert);
    DataResult<List<JobSeekerFavoriteJobAdvert>> getAllJobSeekerId(int id);
}
