package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.JobExperienceService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.ErrorDataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.jobExperienceDto.JobExperienceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/jobexperience")
public class JobExperiencesController {
    private JobExperienceService jobExperienceService;

    @Autowired
    public JobExperiencesController(JobExperienceService jobExperienceService) {
        this.jobExperienceService = jobExperienceService;
    }

    @PostMapping("/add")
    public ResponseEntity<Result> add(@Valid @RequestBody JobExperienceDto jobExperienceDto){
        return ResponseEntity.ok(jobExperienceService.add(jobExperienceDto)) ;
    }

    @GetMapping("/getall")
    public DataResult<List<JobExperienceDto>> getAll(){
        return this.jobExperienceService.getAll();
    }

    @GetMapping("/getByOrderByEndedDateDesc")
    public DataResult<List<JobExperienceDto>> getByOrderByEndedDateDesc(){
        return this.jobExperienceService.getByOrderByEndedDateDesc();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDataResult<Object> handleValidationException
            (MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exceptions.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorDataResult<Object> errors
                = new ErrorDataResult<Object>(validationErrors, "Doğrulama hataları");
        return errors;
    }
}
