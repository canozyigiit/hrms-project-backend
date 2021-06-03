package hrms.hrmsproject.business.concrete;

import hrms.hrmsproject.business.abstracts.CityService;
import hrms.hrmsproject.business.constants.Messages;
import hrms.hrmsproject.core.utilities.business.BusinessRules;
import hrms.hrmsproject.core.utilities.results.*;
import hrms.hrmsproject.dataAccess.abstracts.CityDao;
import hrms.hrmsproject.entities.concretes.City;
import hrms.hrmsproject.entities.concretes.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityManager implements CityService {
    private CityDao cityDao;

    @Autowired
    public CityManager(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    public Result add(City city) {
        Result result = BusinessRules.Run(checkIfCityNameExists(city));
        if (result != null) {
            return result;
        }
        this.cityDao.save(city);
        return new SuccessResult(Messages.CityAdded);
    }

    @Override
    public DataResult<List<City>> getAll() {
        return new SuccessDataResult<List<City>>(this.cityDao.findAll(),Messages.CityListed);
    }

    private Result checkIfCityNameExists(City city) {
        var result = cityDao.findAllByName(city.getName()).stream().count() != 0;
        if (result) {
            return new ErrorResult(Messages.cityExists);
        }
        return new SuccessResult();
    }
}
