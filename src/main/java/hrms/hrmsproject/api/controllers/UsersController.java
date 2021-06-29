package hrms.hrmsproject.api.controllers;


import hrms.hrmsproject.business.abstracts.UserService;
import hrms.hrmsproject.core.entities.User;
import hrms.hrmsproject.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@CrossOrigin
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getbyemail")
    public DataResult<User> getByEmail(String email) {
        return this.userService.getByEmail(email);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<?> changePassword(@RequestParam int id,@RequestParam String password,@RequestParam String newPassword) {
        return ResponseEntity.ok(this.userService.changePassword(id,password,newPassword));
    }
//    @PostMapping("/changeEmail")
//    public  ResponseEntity<?> changeEmail(@Valid @RequestBody User user ){
//        return ResponseEntity.ok(this.userService.changeEmail(user));
//    }
}
