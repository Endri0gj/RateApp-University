package rate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import rate.controller.UserController;
import rate.dto.LoginUserDto;
import rate.model.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    UserController userController;

    @Test
    public void doesUserWithId1Exists(){
        ResponseEntity<?> responseEntity=userController.get(1);
        User expectedUser = new User(1,"klei","null","benjamin.smith11@fti.edu.al","$2a$10$dlHsxh0lymo6muGvReV7e.BoPKz7NR5t11wycc.5EMhj4o4dq5BjS");
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedUser, responseEntity.getBody());
    }
    @Test
    public void userDoNotExists(){
        ResponseEntity responseEntity=userController.get(1000);
        assertEquals(404,responseEntity.getStatusCodeValue());
    }

    @Test
    public void successfulLogin(){
        LoginUserDto loginUser=new LoginUserDto("asd.smith11@fti.edu.al","test12");
        ResponseEntity response=userController.login(loginUser);
        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    public void unsuccessfulLogin(){
        LoginUserDto loginUser=new LoginUserDto("wwwwwwwww@www.ww","test12");
        ResponseEntity response=userController.login(loginUser);
        assertEquals(404,response.getStatusCodeValue());
    }

    @Test
    public void donotSaveUserWhichAlreadyHaveAnAccount(){
        User user = new User(1,"klei","null","benjamin.smith11@fti.edu.al","$2a$10$dlHsxh0lymo6muGvReV7e.BoPKz7NR5t11wycc.5EMhj4o4dq5BjS");
        ResponseEntity response=userController.save(user);
        assertEquals(400,response.getStatusCodeValue());
    }

}
