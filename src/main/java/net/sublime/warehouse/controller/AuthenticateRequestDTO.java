package net.sublime.warehouse.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class AuthenticateRequestDTO {
    String email;
    String password;
}
