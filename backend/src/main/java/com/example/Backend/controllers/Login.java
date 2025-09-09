package com.example.Backend.controllers;


import com.example.Backend.config.Jwt;
import com.example.Backend.dto.Response;
import com.example.Backend.dto.User_dto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import com.example.Backend.config.Log;//Log.log.info("");
import java.util.HashMap;
import java.util.Map;

import com.example.Backend.dto.Login_dto;
import com.example.Backend.services.Login_services;
import com.example.Backend.models.User;
@RestController
@RequestMapping("/")
public class Login {
    private Login_services login;

    private Jwt jwt;
    Login(Login_services login,Jwt jwt){
        this.login = login;
        this.jwt = jwt;
    }

    @GetMapping("/csrf-token")
    public CsrfToken csrf(CsrfToken token) {
        return token; // contains headerName, parameterName, and token value
    }

    @PostMapping("/signup")
    public User signup(@RequestBody Login_dto user ){
        System.out.println(user.toString());
        Log.log.info("Signin attempt for user: {}", user.getMail());
        User res = login.signup(user);
        return res;
    }

    @PostMapping("/signin")
    public ResponseEntity<Response<User_dto>> signin(@RequestBody Login_dto user ){
        Response<User_dto> res=login.signin(user);
        String refreshToken=jwt.refresh_Token(res.getData().getId());
        ResponseCookie cookie=ResponseCookie.from("token",refreshToken)
                .httpOnly(true)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(7*24*60*60)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(res);
    }

    @GetMapping("/refresh")
    public Response refresh(@CookieValue(value = "token")String access_token){
        Response res = new Response();
        try {
            String validity = jwt.extract_token(access_token);
            Log.log.info("Validity: {}", validity);
            String refresh_token = jwt.refresh_Token(access_token);
            res.setRefresh_token(refresh_token);
            res.setMsg(true);
            res.setMessage("token validated adn generated");
            return res;
            }catch(Exception e){
            res.setMsg(false);
            res.setMessage(e.getMessage());
            return res;
        }

    }

}
