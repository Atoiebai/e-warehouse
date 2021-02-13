package net.sublime.warehouse.controller;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.user.User;
import net.sublime.warehouse.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping()
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/success")
    public ResponseEntity<String> auth() {
        return new ResponseEntity<>("Congrats" , HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll() , HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(
            @RequestBody User user,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userService.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
