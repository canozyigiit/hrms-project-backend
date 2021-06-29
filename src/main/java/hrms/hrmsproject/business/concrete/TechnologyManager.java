package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.TechnologyService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.TechnologyDao;
import hrms.hrmsproject.entities.concretes.Technology;
import hrms.hrmsproject.entities.dtos.technologyDtos.TechnologyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TechnologyManager implements TechnologyService {
    private TechnologyDao technologyDao;
    private DtoConverterService dtoConverterService;

    @Autowired
    public TechnologyManager(TechnologyDao technologyDao, DtoConverterService dtoConverterService) {
        this.technologyDao = technologyDao;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(TechnologyDto technologyDto) {
        this.technologyDao.save((Technology) this.dtoConverterService.dtoClassConverter(technologyDto, Technology.class));
        return new SuccessResult(Messages.technologyAdded);
    }

    @Override
    public Result update(int id, String description) {
        Technology technology = this.technologyDao.findById(id).orElse(null);
        if (description != null) {
            technology.setDescription(description);
            this.technologyDao.save(technology);
            return new SuccessResult("Tecknoloji Açıklaması Güncellendi");
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
