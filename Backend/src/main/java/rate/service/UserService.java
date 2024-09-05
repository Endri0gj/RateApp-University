package rate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rate.configuration.PasswordUtils;
import rate.dto.LoginUserDto;
import rate.dto.UserDetailsDto;
import rate.exception.RateAppException;
import rate.model.User;
import rate.repository.UserRepo;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;


    public User get (Integer id){
        Optional<User> user=userRepo.findById(id);
        if(user.isPresent())
            return user.get();
        else throw new NoSuchElementException();
    }

    public boolean existByEmail(String email){
        return userRepo.countUsersWithEmail(email)!=0;
    }

    public User save (User user){
        if(!existByEmail(user.getEmail())) {
            String hashedPass=PasswordUtils.hashPassword(user.getPassword());
            user.setPassword(hashedPass);
            return userRepo.save(user);

        }
        else throw new RuntimeException();
    }

    public User getUserByEmail(String email){
        return userRepo.getUserByEmail(email);
    }
    public UserDetailsDto login(LoginUserDto loginUserDto,User user){
        String password=loginUserDto.getPassword();
        if(PasswordUtils.verifyPassword(password,user.getPassword()))
            return  new UserDetailsDto(user);
        return  null;
    }

}
