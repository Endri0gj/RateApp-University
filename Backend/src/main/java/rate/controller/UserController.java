package rate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rate.dto.LoginUserDto;
import rate.dto.UserDetailsDto;
import rate.exception.RateAppException;
import rate.model.User;
import rate.service.UserService;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Integer id){
        try{
            User user = userService.get(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }catch (NoSuchElementException e){
            String message=RateAppException.noSuchElement("id",id);
            return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
        }
    }

    @CrossOrigin("*")
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody User user){
        try{
            user=userService.save(user);
            return new ResponseEntity<String>("You have successfully registered. Welcome to our community!",HttpStatus.CREATED);
        }
        catch (RuntimeException e){
            String message=RateAppException.alreadyRegistered("email",user.getEmail());
            return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
        }
      }

    @CrossOrigin("*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDto loginUserDto) {
        String email = loginUserDto.getEmail();
        User user=userService.getUserByEmail(loginUserDto.getEmail());
        if(user==null) {
            String message = RateAppException.noSuchElement("email", email);
            return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
        }

        UserDetailsDto userDetailsDto = userService.login(loginUserDto,user);
        if(userDetailsDto==null)
            return new ResponseEntity<String>("Incorrect password", HttpStatus.FORBIDDEN);
        return new ResponseEntity<UserDetailsDto>( userDetailsDto, HttpStatus.OK);


    }

}
