package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.JobSeekerFavoriteJobAdvertService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.JobSeekerFavoriteJobAdvertDao;
import hrms.hrmsproject.entities.concretes.JobSeekerFavoriteJobAdvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerFavoriteAdvertManager implements JobSeekerFavoriteJobAdvertService {
    private JobSeekerFavoriteJobAdvertDao jobSeekerFavoriteJobAdvertDao;

    @Autowired
    public JobSeekerFavoriteAdvertManager(JobSeekerFavoriteJobAdvertDao jobSeekerFavoriteJobAdvertDao) {
        this.jobSeekerFavoriteJobAdvertDao = jobSeekerFavoriteJobAdvertDao;
    }

    @Override
    public DataResult<List<JobSeekerFavoriteJobAdvert>> getAll() {
        return new SuccessDataResult<List<JobSeekerFavoriteJobAdvert>>(this.jobSeekerFavoriteJobAdvertDao.findAll());
    }

    @Override
    public Result add(JobSeekerFavoriteJobAdvert jobSeekerFavoriteJobAdvert) {
        this.jobSeekerFavoriteJobAdvertDao.save(jobSeekerFavoriteJobAdvert);
        return new SuccessResult("Added");
    }

    @Override
    public Result delete(JobSeekerFavoriteJobAdvert jobSeekerFavoriteJobAdvert) {
        this.jobSeekerFavoriteJobAdvertDao.delete(jobSeekerFavoriteJobAdvert);
        return new SuccessResult("Deleted");
    }

    @Override
    public DataResult<List<JobSeekerFavoriteJobAdvert>> getAllJobSeekerId(int id) {
        return new SuccessDataResult<List<JobSeekerFavoriteJobAdvert>>(this.jobSeekerFavoriteJobAdvertDao.getByJobSeeker_Id(id));
    }
}
