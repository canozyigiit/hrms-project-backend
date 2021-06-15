package hrms.hrmsproject.api.controllers;

import hrms.hrmsproject.business.abstracts.ConfirmJobAdvertService;
import hrms.hrmsproject.core.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confirmJobAdvert")
@CrossOrigin
public class ConfirmJobAdvertsController {
    private ConfirmJobAdvertService confirmJobAdvertService;

    @Autowired
    public ConfirmJobAdvertsController(ConfirmJobAdvertService confirmJobAdvertService) {
        this.confirmJobAdvertService = confirmJobAdvertService;
    }

    @PutMapping("/{jobAdvertId}")
    public Result update(@PathVariable("jobAdvertId") int jobAdvertId, int systemPersonnelId) {

        return confirmJobAdvertService.confirmJobAdvert(jobAdvertId,systemPersonnelId);
    }
}
