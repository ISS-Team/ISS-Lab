package cmsteam2.rest.controller;

import cmsteam2.backend.GenericRepository;
import cmsteam2.backend.UsersRepository;
import cmsteam2.common.domain.User;
import cmsteam2.middleware.Main;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public User login(@RequestBody User user, HttpSession session) {
//        System.out.println(user);
        User actualUser = usersRepository.login(user);
        if (actualUser != null) {
            session.setAttribute("username", actualUser.getUsername());
            return actualUser;
        } else {
            return null;
        }
    }

    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity register(@RequestBody User user) {
        if (usersRepository.usernameExists(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } else {
            usersRepository.save(user);
            return ResponseEntity.ok("{}");
        }
    }

}
