package hrms.hrmsproject.business.abstracts;


import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.JobSeeker;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JobSeekerService {
    Result add(JobSeeker jobSeeker);

    DataResult<List<JobSeeker>> getAll();

    DataResult<JobSeeker> getById(int id);
    Result validateJobSeeker(int id);

    Result delete(JobSeeker jobSeeker);

    Result addImageJobSeeker(MultipartFile file, int jobSeekerId);

}
