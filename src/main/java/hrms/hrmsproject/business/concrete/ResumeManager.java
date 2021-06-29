package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.ResumeService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.core.utilities.uploads.FileService;
import hrms.hrmsproject.dataAccess.abstracts.ResumeDao;
import hrms.hrmsproject.entities.concretes.Resume;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeAddDto;
import hrms.hrmsproject.entities.dtos.resumeDtos.ResumeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ResumeManager implements ResumeService {
    private ResumeDao resumeDao;
    private FileService fileService;
    private DtoConverterService dtoConverterService;

    @Autowired
    public ResumeManager(ResumeDao resumeDao, FileService fileService, DtoConverterService dtoConverterService) {
        this.resumeDao = resumeDao;
        this.fileService = fileService;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(ResumeAddDto resumeAddDto) {
        this.resumeDao.save((Resume) this.dtoConverterService.dtoClassConverter(resumeAddDto, Resume.class));
        return new SuccessResult(Messages.resumeAdded);
    }

    @Override
    public Result update(Resume resume) {
        Resume rsm = this.resumeDao.findById(resume.getId()).orElse(null);
        LocalDate date = LocalDate.now();
        if (rsm != null) {
            rsm.setLinkedLink(resume.getLinkedLink());
            rsm.setGithubLink(resume.getGithubLink());
            rsm.setDescription(resume.getDescription());
            rsm.setPhoto(resume.getPhoto());
            resumeDao.save(rsm);
            return new SuccessResult("CV Güncellendi");
        }

        return new ErrorResult("CV Güncellenemedi");
    }

    @Override
    public DataResult<List<ResumeDto>> getAll() {
        return new SuccessDataResult<List<ResumeDto>>(this.dtoConverterService.dtoConverter(this.resumeDao.findAll(), ResumeDto.class), Messages.resumeListed);
    }

    @Override
    public DataResult<Resume> getById(int id) {
        return new SuccessDataResult<Resume>(this.resumeDao.findById(id).orElse(null), Messages.resumeGet);
    }

    @Override
    public DataResult<List<ResumeDto>> findAllByJobSeekerId(int id) {
        return new SuccessDataResult<List<ResumeDto>>(this.dtoConverterService.dtoConverter(resumeDao.findAllByJobSeekerId(id), ResumeDto.class));
    }

    @Override
    public DataResult<ResumeDto> getByJobSeeker_Id(int id) {

        return new SuccessDataResult<ResumeDto>((ResumeDto) this.dtoConverterService.dtoClassConverter(resumeDao.findByJobSeekerId(id), ResumeDto.class));
    }

    public Result addImageResume(MultipartFile file, int resumeId) {

        Map<String, String> uploader = (Map<String, String>) fileService.save(file).getData();
        String imageUrl = uploader.get("url");
        Resume Cv = resumeDao.getOne(resumeId);
        Cv.setPhoto(imageUrl);
        resumeDao.save(Cv);
        return new SuccessResult("Image added");

    }
}
