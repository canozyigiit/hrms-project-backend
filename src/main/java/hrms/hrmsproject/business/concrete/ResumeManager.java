package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.ResumeService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
import hrms.hrmsproject.dataAccess.abstracts.ResumeDao;
import hrms.hrmsproject.entities.concretes.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResumeManager implements ResumeService {
    private ResumeDao resumeDao;

    @Autowired
    public ResumeManager(ResumeDao resumeDao) {
        this.resumeDao = resumeDao;
    }

    @Override
    public Result add(Resume resume) {
        this.resumeDao.save(resume);
        return new SuccessResult(Messages.resumeAdded);
    }

    @Override
    public DataResult<List<Resume>> getAll() {
        return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll(),Messages.resumeListed);
    }

    @Override
    public DataResult<Resume> getById(int id) {
        return new SuccessDataResult<Resume>(this.resumeDao.findById(id).orElse(null),Messages.resumeGet);
    }

    @Override
    public DataResult<Resume> getByJobSeekerId(int id) {
        return new SuccessDataResult<Resume>(this.resumeDao.getByJobSeekerId(id));
    }
}
