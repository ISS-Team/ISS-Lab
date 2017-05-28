package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.UsersRepository;
import cmsteam2.common.domain.User;
import cmsteam2.middleware.Main;
import org.json.JSONObject;
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
        usersRepository = new UsersRepository(GenericRepository.loadProps(), Main.sessionFactory);
    }

    @GetMapping
    public String getHello() {
        return "HELOO BOY!";
    }

    @PostMapping
    public ResponseEntity login(@RequestBody User user) {
//        System.out.println(user);
        String password = usersRepository.getPassword(user.getUsername());
        if (user.getPassword().equals(password)) {
            return ResponseEntity.ok().body(null);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping
    public ResponseEntity register(@RequestBody User user) {
        if (usersRepository.checkUsername(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            usersRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

}
