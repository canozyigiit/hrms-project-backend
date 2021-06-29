package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.JobExperienceService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.jobExperienceDto.JobExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/jobexperience")
@CrossOrigin
public class JobExperiencesController {
    private JobExperienceService jobExperienceService;

    @Autowired
    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody JobExperienceDto jobExperienceDto){
        return ResponseEntity.ok(jobExperienceService.add(jobExperienceDto)) ;
    }

    @GetMapping("/getall")
    public DataResult<List<JobExperienceDto>> getAll(){
        return this.jobExperienceService.getAll();
    }

    @GetMapping("/getByOrderByEndedDateDesc")
    public DataResult<List<JobExperienceDto>> findAllByResumeIdOrderByEndedDateDesc(@PathVariable int id){
        return this.jobExperienceService.findAllByResumeIdOrderByEndedDateDesc(id);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestParam int id,String companyName, LocalDate endedDate, String position, LocalDate startedDate){
        return ResponseEntity.ok(this.jobExperienceService.update(id,companyName,endedDate,position,startedDate));
    }

    @PostMapping("/delete")
    public Result delete(int id){
        return this.jobExperienceService.delete(id);
    }


}
