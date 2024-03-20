package com.grayhat.apicriptografiausuarios.service;

import com.grayhat.apicriptografiausuarios.model.Users;
import com.grayhat.apicriptografiausuarios.repository.IUsersRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import java.util.Optional;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 *
 * @author grayhat
 */
@Service
public class UsersService {

    private final IUsersRepo usersRepo;
    private final String jwtSecret;

    @Autowired
    public UsersService(IUsersRepo usersRepo, @Value("${jwt.secret}") String jwtSecret) {
        this.usersRepo = usersRepo;
        this.jwtSecret = jwtSecret;
    }

    public List<Users> findAllUsers() {
        return usersRepo.findAll();
    }

    public Optional<Users> findUserByEmail(String email) {
        return usersRepo.findByEmail(email);
    }

    public Optional<Users> findUserByUsername(String username) {
        return usersRepo.findByUsername(username);
    }

    public boolean checkUserLogin(String email, String password) {
        Optional<Users> user = findUserByEmail(email);

        String emailLogged = user.get().getEmail();
        String passwordHash = user.get().getPasswordHash();
        boolean status = user.get().isEnabled();
        boolean validPassword = decryptPasswordUSer(password, passwordHash);

        if (email.equals(emailLogged) && validPassword && status) {
            return validPassword;
        } else {
            return false;
        }
    }

    public Users saveUser(Users user) {
        //Validamos los datos
        if (validUserData(user)) {
            //Encriptamos la contraseña
            String[] encryptionResult = encryptPasswordUser(user.getPasswordHash());
            String passwordHashed = encryptionResult[0];
            String salt = encryptionResult[1];

            user.setPasswordHash(passwordHashed);
            user.setSalt(salt);

            //Generamos el token
            String token = generateToken(user.getUsername());
            user.setToken(token);

            return usersRepo.save(user);
        }

        return null;
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

    private boolean decryptPasswordUSer(String password, String passwordHashed) {
        boolean decryptedPassword = false;

        try {
            decryptedPassword = BCrypt.checkpw(password, passwordHashed);
        } catch (Exception e) {
        }

        return decryptedPassword;
    }

}
