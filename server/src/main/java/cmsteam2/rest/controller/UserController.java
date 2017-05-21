package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.UsersRepository;
import cmsteam2.common.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;

/**
 * Created by Costi on 15.05.2017.
 */

@RestController
@RequestMapping("/users")
public class UserController {


    public UsersRepository usersRepository;

    public UserController() throws FileNotFoundException {
       usersRepository=new UsersRepository(GenericRepository.loadProps());
    }

    @GetMapping
    public String getHello(){
        return "HELOO BOY!";
    }

    @PostMapping
    public ResponseEntity login(@RequestBody User user){
//        System.out.println(user);
        usersRepository.login(user);
        if(user.password.equals("costi")){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
