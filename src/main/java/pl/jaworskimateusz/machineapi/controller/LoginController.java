package pl.jaworskimateusz.machineapi.controller;

import ch.qos.logback.classic.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.jaworskimateusz.machineapi.config.LoginCredentials;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody LoginCredentials credentials) {

    }

    @GetMapping("/token")
    public String getToken(HttpServletRequest request, HttpServletResponse response) {

        return response.getStatus() + "pies";
    }
}
