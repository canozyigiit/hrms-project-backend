package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.JobWorkSpaceTypeService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.JobWorkSpaceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jobworkspacetype")
@CrossOrigin
public class JobWorkSpacesController {

    private JobWorkSpaceTypeService jobWorkSpaceTypeService;

    @Autowired
    public JobWorkSpacesController(JobWorkSpaceTypeService jobWorkSpaceTypeService) {
        this.jobWorkSpaceTypeService = jobWorkSpaceTypeService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobWorkSpaceType>> getAll(){
        return this.jobWorkSpaceTypeService.getAll();
    }
}
