package hrms.hrmsproject.business.abstracts;

import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Resume;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeAddDto;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {

    Result add(ResumeAddDto resumeAddDto);

    DataResult<List<ResumeDto>> getAll();

    DataResult<Resume> getById(int id);

    DataResult<List<ResumeDto>> findAllByJobSeekerId(int id);
    DataResult<ResumeDto> getByJobSeeker_Id(int id);

    Result addImageResume(MultipartFile file, int resumeId);
}
