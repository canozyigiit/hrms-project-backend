package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.LanguageService;
import hrms.hrmsproject.core.utilities.results.DataResult;
import hrms.hrmsproject.core.utilities.results.Result;
import hrms.hrmsproject.entities.concretes.Language;
import hrms.hrmsproject.entities.dtos.languageDtos.LanguageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/languages")
@CrossOrigin
public class LanguagesController {
    private LanguageService languageService;

    @Autowired
    public LanguagesController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@Valid @RequestBody LanguageDto languageDto){
        return ResponseEntity.ok(this.languageService.add(languageDto));
    }

    @GetMapping("/getall")
    public DataResult<List<LanguageDto>> getAll(){
        return this.languageService.getAll();
    }


    @GetMapping("/getByResumeId")
    public DataResult<List<LanguageDto>> getByResumeId(int id){
        return this.languageService.getByResumeId(id);
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@Valid @RequestBody Language language){
        return ResponseEntity.ok(this.languageService.update(language));
    }
    @PostMapping("/delete")
    public Result delete(int id){
        return this.languageService.delete(id);
    }
}
