package com.yael.springboot.api.gchat.gchat.presentation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.RegisterUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UpdatePasswordOrEmailDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UpdateUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.VerifyAccountDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.application.services.AuthService;
import com.yael.springboot.api.gchat.gchat.application.services.ResponseService;

import jakarta.validation.Valid;




@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<ResponseService<IUserAuthProjection>> register( @Valid @RequestBody RegisterUserDto registerUserDto ){
        ResponseService<IUserAuthProjection> response = authService.Register(registerUserDto);

        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseService<IUserAuthProjection>> updateUser( @Valid @ModelAttribute UpdateUserDto entity) {
        ResponseService<IUserAuthProjection> response = authService.updateUser(entity);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("/get-user")
    public ResponseEntity<ResponseService<IUserAuthProjection>> postMethodName(
        @Param("email") String email,
        @Param("id") Long id
    ) {
        var response = authService.getUser(id, email);
        return ResponseEntity.status(200).body(response);
    }

    @GetMapping("/validate-token")
    public ResponseEntity<ResponseService<IUserAuthProjection>> validateToken(){
        var response = authService.getUserByToken();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/verify-account")
    public ResponseEntity<ResponseService<String>> verifyAccount( @Valid @RequestBody VerifyAccountDto verifyAccountDto ){
        var res = this.authService.verifyAccount(verifyAccountDto);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @GetMapping("/verify-code-auth/{code}")
    public ResponseEntity<ResponseService<Boolean>> verifyCodeAuth( @PathVariable String code ){
        var res = this.authService.verifyCodeAuth(code);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @PutMapping("/update-password-email")
    public ResponseEntity<ResponseService<IUserAuthProjection>> updatePasswordOrEmail(@Valid @RequestBody UpdatePasswordOrEmailDto update) {
        var res = authService.updateEmailOrPassword(update);
        return ResponseEntity.status(res.getStatus()).body(res);
    }

    @DeleteMapping
    public ResponseEntity<ResponseService<Boolean>> delete(){
        var response = authService.deleteAccount();
        return ResponseEntity.status(response.getStatus()).body(response);
    }


}
