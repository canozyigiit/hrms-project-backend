package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.TechnologyService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.ResumeDao;
import hrms.hrmsproject.dataAccess.abstracts.TechnologyDao;
import hrms.hrmsproject.entities.concretes.Resume;
import hrms.hrmsproject.entities.concretes.Technology;
import hrms.hrmsproject.entities.dtos.technologyDtos.TechnologyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyDao technologyDao;
    private DtoConverterService dtoConverterService;
    private ResumeDao resumeDao;

    @Autowired
    public TechnologyManager(TechnologyDao technologyDao, ResumeDao resumeDao, DtoConverterService dtoConverterService) {
        this.technologyDao = technologyDao;
        this.dtoConverterService = dtoConverterService;
        this.resumeDao = resumeDao;
    }

    @Override
    public Result add(TechnologyDto technologyDto) {
        this.technologyDao.save((Technology) this.dtoConverterService.dtoClassConverter(technologyDto, Technology.class));
        return new SuccessResult(Messages.technologyAdded);
    }

    @Override
    public Result update(TechnologyDto technologyDto) {
        Technology technology = this.technologyDao.findById(technologyDto.getId()).orElse(null);
        Resume resume = this.resumeDao.findById(technologyDto.getResumeId()).orElse(null);

        if (technology != null) {
            technology.setDescription(technologyDto.getDescription());
            technology.setResume(resume);
            this.technologyDao.save(technology);
            return new SuccessResult("Tecknoloji Güncellendi");
        }
        return new ErrorResult("Bilgiler Güncellenemedi");
    }

    @Override
    public Result delete(int id) {
        this.technologyDao.deleteById(id);
        return new SuccessResult("Teknoloji silindi");
    }

    @Override
    public DataResult<List<TechnologyDto>> getAll() {
        return new SuccessDataResult<List<TechnologyDto>>(this.dtoConverterService.dtoConverter(this.technologyDao.findAll(), TechnologyDto.class), Messages.technologyListed);
    }

    @Override
    public DataResult<Technology> getById(int id) {
        return new SuccessDataResult<Technology>(this.technologyDao.findById(id).orElse(null));
    }
}
