package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.UsersRepository;
import cmsteam2.common.domain.User;
import cmsteam2.middleware.Main;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/users")
public class UserController {

    public UsersRepository usersRepository;

    public UserController() throws FileNotFoundException {
        usersRepository = new UsersRepository(GenericRepository.loadProps(), Main.sessionFactory);
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
//        System.out.println(user);
        String password = usersRepository.getPassword(user.getUsername());
        if (user.getPassword().equals(password)) {
            return ResponseEntity.ok().body("{}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        if (usersRepository.usernameExists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            usersRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }

}
