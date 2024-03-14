package com.grayhat.apicriptografiausuarios.service;

import com.grayhat.apicriptografiausuarios.model.Users;
import com.grayhat.apicriptografiausuarios.repository.IUsersRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author grayhat
 */
@Service
public class UsersService {

    private final IUsersRepo usersRepo;
    private final PasswordEncoder passwordEncoder;
    private final String jwtSecret;

    @Autowired
    public UsersService(IUsersRepo usersRepo, PasswordEncoder passwordEncoder, @Value("${jwt.secret}") String jwtSecret) {
        this.usersRepo = usersRepo;
        this.passwordEncoder = passwordEncoder;
        this.jwtSecret = jwtSecret;
    }

    public List<Users> findAllUsers() {
        return usersRepo.findAll();
    }

    public Optional<Users> findUserByEmail(String email) {
        return usersRepo.findByEmail(email);
    }
    
    public Optional<Users> findUserByUsername(String username){
        return usersRepo.findByUsername(username);
    }

    public boolean checkUserLogin(String email, String password) {
        Optional<Users> user = findUserByEmail(email);

        if (user.isPresent()) {
            return passwordEncoder.matches(password, user.get().getPasswordHash());
        }

        return false;
    }

    public Users saveUser(Users user) {
        //Validamos los datos
        if (validUserData(user)) {
            //Encriptamos la contraseña
            user.setPasswordHash(encryptPasswordUser(user.getPasswordHash()));

            //Generamos el token
            String token = generateToken(user.getUsername());
            user.setToken(token);

            return usersRepo.save(user);
        }

        return null;
    }

    private String encryptPasswordUser(String password) {
        return passwordEncoder.encode(password);
    }

    private String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    private boolean validUserData(Users user) {
        //Validamos que los datos no sean null
        return !(user.getFirstName() == null && user.getLastName() == null
                || user.getRole() == null
                || user.getEmail() == null && user.getUsername() == null
                || user.getPasswordHash() == null && user.isEnabled());
    }

}
