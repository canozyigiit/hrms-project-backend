package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.JobTypeService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.JobType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jobType")
@CrossOrigin
public class JopTypesController {
    private JobTypeService jobTypeService;

    @Autowired
    public JopTypesController(JobTypeService jobTypeService) {
        this.jobTypeService = jobTypeService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobType>> getAll(){
        return this.jobTypeService.getAll();
    }
}
