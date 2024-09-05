package rate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rate.dto.LoginUserDto;
import rate.dto.UserDetailsDto;
import rate.model.User;

public class DtoTest {

    UserDetailsDto userDetailsDto;
    LoginUserDto loginUserDto;

    @Test()
    public void userIsConvertedSuccessfullyToUserDto(){
        User user=new User(1,"Test","Test","test@test.com","test12");
        UserDetailsDto userDto=new UserDetailsDto(user);
        Assertions.assertEquals("Test",userDto.getName());
    }

    @Test()
    public void loginDtoIsGettingDataSuccessfully(){
        loginUserDto=new LoginUserDto("test@tes.com","test");
        Assertions.assertEquals("test@tes.com",loginUserDto.getEmail());
    }
}
