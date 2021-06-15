package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.JobAdvertService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.ErrorDataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertAddDto;
import hrms.hrmsproject.entities.dtos.jobAdvertDtos.JobAdvertDto;
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
@RequestMapping("api/jobadverts")
@CrossOrigin
public class JobAdvertsController {
    private JobAdvertService jobAdvertService;

    @Autowired
    public JobAdvertsController(JobAdvertService jobAdvertService) {
        this.jobAdvertService = jobAdvertService;
    }

//    @PostMapping("/add")
//    public ResponseEntity<?> add(@Valid @RequestBody JobAdvert jobAdvert) {
//        return ResponseEntity.ok(this.jobAdvertService.add(jobAdvert));
//    }
    @PostMapping("add")
    public ResponseEntity<?> addJobAdvert(@Valid @RequestBody JobAdvertAddDto jobAdvertAddDto) {
        return ResponseEntity.ok(this.jobAdvertService.add(jobAdvertAddDto));
    }
    @GetMapping("getbyid/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return ResponseEntity.ok(this.jobAdvertService.getById(id));
    }

    @GetMapping("/getall")
    public DataResult<List<JobAdvertDto>> getAll() {
        return this.jobAdvertService.getAll();
    }

    @PostMapping("/changeOpenToClose")
    public Result changeOpenToClose(int id) {
        return this.jobAdvertService.changeOpenToClose(id);
    }

    @GetMapping("/getAllOpenTrueJobAdvertList")
    public DataResult<List<JobAdvertDto>> getAllOpenTrueJobAdvertList() {
        return this.jobAdvertService.getAllOpenJobAdvertList();
    }

    @GetMapping("/getAllisOpenTrueAndCity_Id")
    public  DataResult<List<JobAdvertDto>> getAllisOpenTrueAndCity_Id(int id){
        return  this.jobAdvertService.getAllisOpenTrueAndCity_Id(id);
    }
    @GetMapping("/getByisOpenTrueOrderByCreatedDateDesc")
    public DataResult<List<JobAdvertDto>>getByisOpenTrueOrderByCreatedDateDesc(){
        return this.jobAdvertService.getByisOpenTrueOrderByCreatedDateDesc();
    }

    @GetMapping("/getAllOpenTrueJobAdvertByEmployer")
    public DataResult<List<JobAdvertDto>> getByisOpenTrueAndEmployer_Id(int id) {
        return this.jobAdvertService.getByisOpenTrueAndEmployer_Id(id);
    }

//    @GetMapping("/getalldto")
//    public DataResult<List<JobAdvertDto>> getAllDto(){
//        return this.jobAdvertService.getAllDto();
//    }


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
