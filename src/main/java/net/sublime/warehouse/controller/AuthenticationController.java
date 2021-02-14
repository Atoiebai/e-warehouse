package net.sublime.warehouse.controller;

import lombok.AllArgsConstructor;
import net.sublime.warehouse.model.user.User;
import net.sublime.warehouse.reposirtory.UserRepository;
import net.sublime.warehouse.security.JwtTokenProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticateRequestDTO request) {
        try {
           String email = request.getEmail();
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, request.getPassword()));
           User user = userRepository.findByEmail(email).orElseThrow();
           String token = jwtTokenProvider.createToken(request.getEmail() , user.getRole().name());
            Map<Object , Object> response = new HashMap<>();
            response.put("email" , request.getEmail());
            response.put("token" , token);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return  new ResponseEntity<>("Invalid email or URL" , HttpStatus.FORBIDDEN);
        }
    }
}

