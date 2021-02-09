package net.sublime.warehouse.controller;

import net.sublime.warehouse.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> registerUser(
            @RequestBody User user,
            BindingResult bindingResult) {
        if(bindingResult.hasErrors()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
