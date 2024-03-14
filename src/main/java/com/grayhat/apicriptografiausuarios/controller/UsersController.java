package com.grayhat.apicriptografiausuarios.controller;

import com.grayhat.apicriptografiausuarios.dto.UsersDto;
import com.grayhat.apicriptografiausuarios.model.Users;
import com.grayhat.apicriptografiausuarios.service.UsersService;
import com.grayhat.apicriptografiausuarios.util.Mapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author grayhat
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<UsersDto>> listUsers() {
        try {
            //Recuperamos los usuarios desde la base de datos
            List<Users> users = usersService.findAllUsers();

            //Convertimos los usuarios de entidad a un objeto DTO
            List<UsersDto> usersDto = new ArrayList<>();

            for (Users user : users) {
                UsersDto userDto = Mapper.mapEntityToDto(user, UsersDto.class);
                usersDto.add(userDto);
            }

            return ResponseEntity.ok(usersDto);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(System.out);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UsersDto userDto) {
        try {
            UsersDto usersDto = new UsersDto.Builder()
                    .role(userDto.getRole())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
                    .passwordHash(userDto.getPasswordHash())
                    .enabled(userDto.isEnabled())
                    .build();

            Users user = Mapper.mapDtoToEntity(usersDto, Users.class);
            Users savedUser = usersService.saveUser(user);

            if (savedUser != null) {
                return ResponseEntity.ok("Usuario Registro Correctamente en Base de Datos");
            } else {
                return ResponseEntity.badRequest().body("Datos de Usuario Invalidos");
            }

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(System.err);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Registro un Nuevo Usuario");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsersDto userDto) {
        try {
            UsersDto userLogged = new UsersDto.Builder()
                    .email(userDto.getEmail())
                    .passwordHash(userDto.getPasswordHash())
                    .enabled(userDto.isEnabled())
                    .build();

            Users user = Mapper.mapDtoToEntity(userLogged, Users.class);

            boolean loginSuccess = usersService.checkUserLogin(user.getEmail(), user.getPasswordHash());

            if (loginSuccess) {
                return ResponseEntity.ok("Usuario logueado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o Contraseña son inválidos");
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(System.err);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Iniciar Sesión");
        }
    }

}
