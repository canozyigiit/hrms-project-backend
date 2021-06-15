package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.JobStyleService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.entities.concretes.JobStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/jobStyle")
@CrossOrigin
public class JobStylesController {

    private JobStyleService jobStyleService;

    @Autowired
    public JobStylesController(JobStyleService jobStyleService) {
        this.jobStyleService = jobStyleService;
    }

    @GetMapping("/getall")
    public DataResult<List<JobStyle>> getAll(){
        return this.jobStyleService.getAll();
    }
}
