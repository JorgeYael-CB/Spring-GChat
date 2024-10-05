package com.yael.springboot.api.gchat.gchat.application.services;


import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yael.springboot.api.gchat.gchat.application.dtos.auth.RegisterUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.UpdateUserDto;
import com.yael.springboot.api.gchat.gchat.application.dtos.auth.VerifyAccountDto;
import com.yael.springboot.api.gchat.gchat.application.interfaces.projections.IUserAuthProjection;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IRolesRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.repositories.IUserRepository;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.ICodeAuthGenerator;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IFilesService;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IJwtService;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IMailerService;
import com.yael.springboot.api.gchat.gchat.application.interfaces.services.IValidateClassService;
import com.yael.springboot.api.gchat.gchat.application.mappers.AutoMapper;
import com.yael.springboot.api.gchat.gchat.domain.entities.PhotoEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.RoleEntity;
import com.yael.springboot.api.gchat.gchat.domain.entities.UserEntity;
import com.yael.springboot.api.gchat.gchat.domain.exceptions.CustomException;
import com.yael.springboot.api.gchat.gchat.infrastructure.services.GetUserByAuth;





@Service
public class AuthService {

    @Autowired
    IUserRepository userRepository;
    @Autowired
    IRolesRepository rolesRepository;
    @Autowired
    AutoMapper mapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    IFilesService filesService;
    @Autowired
    GetUserByAuth getUserByAuth;
    @Autowired
    IJwtService jwtService;
    @Autowired
    IValidateClassService validateClassService;
    @Autowired
    IMailerService mailerService;
    @Autowired
    ICodeAuthGenerator codeAuth;


    @Transactional
    public ResponseService<IUserAuthProjection> Register( RegisterUserDto registerUserDto ){
        if( userRepository.findUserByEmailOrName(registerUserDto.getEmail(), registerUserDto.getName()).isPresent() ) throw CustomException.badRequestException("User with Username o Email already exists");
        Optional<RoleEntity> userRole = rolesRepository.findByRole("ROLE_USER");
        String passwordBcrypt = passwordEncoder.encode(registerUserDto.getPassword());

        UserEntity newUser = new UserEntity();
        newUser.setName(registerUserDto.getName());
        newUser.setEmail(registerUserDto.getEmail());
        newUser.setPassword(passwordBcrypt);

        if( userRole.isPresent() ) newUser.getRoles().add(userRole.get());

        userRepository.save(newUser);

        String token = this.jwtService.createToken(newUser.getRoles(), newUser.getEmail());
        ResponseService<IUserAuthProjection> response = new ResponseService<>();
        response.setData(userRepository.findUserById(newUser.getId()).get());
        response.setStatus(201);
        response.setToken(token);

        return response;
    }


    @Transactional
    public ResponseService<IUserAuthProjection> updateUser( UpdateUserDto user ){
        Boolean isAllMethodsEmpty = validateClassService.fieldsEmptyClass(UpdateUserDto.class, user);
        if( isAllMethodsEmpty ) throw CustomException.badRequestException("Missing fields to updated");

        UserEntity userDb = userRepository.findByEmail(getUserByAuth.getUsernameLogged())
            .orElseThrow( () -> CustomException.notFoundException("User not found"));

        if (user.getAge() != null) userDb.setAge(user.getAge());
        if (user.getCountry() != null) userDb.setCountry(user.getCountry());
        if (user.getDescription() != null) userDb.setDescription(user.getDescription());
        if (user.getName() != null) userDb.setName(user.getName());
        if (user.getCity() != null) userDb.setCity(user.getCity());

        if( user.getProfileImage() != null ){
            if( userDb.getProfileImage() != null ) filesService.deleteImage(userDb.getProfileImage().getImage(), "images/");
            String imgUrl = filesService.saveImage(user.getProfileImage(), "images/");

            PhotoEntity photoEntity = new PhotoEntity();
            photoEntity.setImage(imgUrl);

            userDb.setProfileImage(photoEntity);
        }

        String token = this.jwtService.createToken(userDb.getRoles(), userDb.getEmail());

        return new ResponseService<>(
            new Date(),
            mapper.userEntityToUserAuth(userDb),
            201,
            token
        );
    }


    @Transactional(readOnly=true)
    public ResponseService<IUserAuthProjection> getUser( Long id, String email ){
        IUserAuthProjection userDb = userRepository.findUserByEmailOrId(email, id)
            .orElseThrow( () -> CustomException.notFoundException("User not exist"));

        ResponseService<IUserAuthProjection> response = new ResponseService<>();
        response.setData(userDb);
        response.setStatus(200);
        response.setDate(new Date());

        return response;
    }


    public ResponseService<IUserAuthProjection> getUserByToken(){
        IUserAuthProjection user = getUserByAuth.getUserProjection();
        String token = jwtService.createToken(user.getRoles(), user.getEmail());

        return new ResponseService<>(new Date(), user, 200, token);
    }

    @Transactional(readOnly = true)
    public ResponseService<String> verifyAccount( VerifyAccountDto verifyAccountDto ){
        String email = verifyAccountDto.getEmail();
        userRepository.findUserByEmailOrName(email, null)
            .orElseThrow( () -> CustomException.badRequestException("User not exists"));
        String code = codeAuth.getCodeGenerator(6);

        // Mandar un email para validar su cuenta y mandarle al front un codigo de verificacion de 1 solo uso.
        StringBuilder html = new StringBuilder();
        html.append("<h1>Verifica tu correo electronico en GCHat<h1/>\n");
        html.append("Se ha solicitado el codigo desde: https://www.gchat.com\n");
        html.append("Tu codigo de verificacion de 1 solo uso es: ");
        html.append(code);
        html.append("<h3>Si tu no solicitaste este codigo, puedes ignorar este mensaje<h3/>");
        html.append("Muchas gracias por tus preferencias, att: GChat");

        this.mailerService.sendEmail(email, html.toString());

        return new ResponseService<>(new Date(), code, 200);
    }



    @Transactional
    public ResponseService<Boolean> deleteAccount(){
        String email = getUserByAuth.getUsernameLogged();
        userRepository.deleteByEmail(email);

        return new ResponseService<>(new Date(), true, 200);
    }

}
