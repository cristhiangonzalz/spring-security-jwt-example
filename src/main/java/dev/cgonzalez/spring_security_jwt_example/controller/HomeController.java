package dev.cgonzalez.spring_security_jwt_example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    @GetMapping
    public String home(Principal principal){
        return "Hello, " + principal.getName();
    }
}
/*
Principal es interfaz que representa la noción abstracta de un Principal,
que se puede utilizar para representar cualquier entidad,
como un individuo, una corporación y una identificación de inicio de sesión.

Cuando un usuario se autentica, Spring Security coloca su identidad en un objeto Authentication.
Principal es una versión simple de esa identidad.
*/