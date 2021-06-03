package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Resume;

import java.util.List;

public interface ResumeService {

    Result add(Resume resume);

    DataResult<List<Resume>> getAll();

    DataResult<Resume> getById(int id);

    DataResult<Resume> getByJobSeekerId(int id);
}
