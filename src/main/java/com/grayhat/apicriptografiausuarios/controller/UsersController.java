package com.grayhat.apicriptografiausuarios.controller;

import com.grayhat.apicriptografiausuarios.dto.UsersDto;
import com.grayhat.apicriptografiausuarios.model.Users;
import com.grayhat.apicriptografiausuarios.service.UsersService;
import com.grayhat.apicriptografiausuarios.util.Mapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final String jwtSecret;

    @Autowired
    public UsersController(UsersService usersService, @Value("${jwt.secret}") String jwtSecret) {
        this.usersService = usersService;
        this.jwtSecret = jwtSecret;
    }

    @GetMapping("/home")
    public String homeController() {
        return "Users is up and running";
    }

    @GetMapping("/listUsers")
    public ResponseEntity<List<UsersDto>> listUsers() {
        try {
            //Recuperamos los usuarios desde la base de datos
            Iterable<Users> users = usersService.findAllUsers();

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
            String[] encryptionResult = encryptPasswordUser(userDto.getPasswordHash());
            String PasswordHash = encryptionResult[0];
            String salt = encryptionResult[1];

            String token = generateToken(userDto.getUsername());

            UsersDto usersDto = new UsersDto.Builder()
                    .role(userDto.getRole())
                    .firstName(userDto.getFirstName())
                    .lastName(userDto.getLastName())
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
                    .passwordHash(PasswordHash)
                    .salt(salt)
                    .token(token)
                    .enabled(userDto.isEnabled())
                    .build();

            Users user = Mapper.mapDtoToEntity(usersDto, Users.class);

            if (encryptionResult != null) {
                Users savedUser = usersService.saveUser(user);

                if (savedUser != null) {
                    return ResponseEntity.ok("Usuario Registro Correctamente en Base de Datos");
                } else {
                    return ResponseEntity.badRequest().body("Datos de Usuario Invalidos");
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al encriptar contraseña");
            }

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(System.err);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Registro un Nuevo Usuario");
        }
    }

    private String[] encryptPasswordUser(String password) {
        String salt = BCrypt.gensalt(12);
        String encryptedPassword;

        try {
            encryptedPassword = BCrypt.hashpw(password, salt);
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.err);
            return null;
        }

        return new String[]{encryptedPassword, salt};
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
