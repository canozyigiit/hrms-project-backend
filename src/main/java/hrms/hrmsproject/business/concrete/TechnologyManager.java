package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.TechnologyService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.dtoConverter.DtoConverterService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.core.utilities.results.SuccessDataResult;
import hrms.hrmsproject.core.utilities.results.SuccessResult;
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
    public TechnologyManager(TechnologyDao technologyDao,DtoConverterService dtoConverterService) {
        this.technologyDao = technologyDao;
        this.dtoConverterService = dtoConverterService;
    }

    @Override
    public Result add(TechnologyDto technologyDto) {
        this.technologyDao.save((Technology) this.dtoConverterService.dtoClassConverter(technologyDto,Technology.class));
        return new SuccessResult(Messages.technologyAdded);
    }

    @Override
    public DataResult<List<TechnologyDto>> getAll() {
        return new SuccessDataResult<List<TechnologyDto>>(this.dtoConverterService.dtoConverter(this.technologyDao.findAll(),TechnologyDto.class),Messages.technologyListed);
    }

    @Override
    public DataResult<Technology> getById(int id) {
        return new SuccessDataResult<Technology>(this.technologyDao.findById(id).orElse(null));
    }
}
