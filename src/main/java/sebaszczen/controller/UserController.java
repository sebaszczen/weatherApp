package sebaszczen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import sebaszczen.dto.UserDto;
import sebaszczen.model.userModel.User;
import sebaszczen.servicesFacade.UserFacade;

import javax.validation.Valid;
import java.util.List;

//@CrossOrigin(origins = "*", allowCredentials = "true", allowedHeaders = "*",methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
@RequestMapping(value = "/users")
public class UserController {

    private UserFacade userFacade;

    @Autowired
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@Valid @RequestBody  UserDto userDto, WebRequest webRequest) {
        if (userFacade.emailAlreadyInUse(userDto)){
            return ResponseEntity.status(HttpStatus.valueOf(409)).header(HttpHeaders.LOCATION, webRequest.getContextPath()).build();
        }
        else {
            User user = userFacade.registerNewUserAccount(userDto);
            return ResponseEntity.ok(user);
        }
    }

    @GetMapping(path = {"/{id}"})
    public UserDto findOne(@PathVariable("id") int id){
        return null;
    }

    @PutMapping
    public UserDto update(@RequestBody UserDto user){
        return null;
    }

    @DeleteMapping(path ={"/{id}"})
    public UserDto delete(@PathVariable("id") int id) {
        return null;
    }

    @GetMapping
    public List findAll(){
        return null;
    }

}
