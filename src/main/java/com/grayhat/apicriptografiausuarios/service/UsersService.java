package com.grayhat.apicriptografiausuarios.service;

import com.grayhat.apicriptografiausuarios.model.Users;
import com.grayhat.apicriptografiausuarios.repository.IUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author grayhat
 */
@Service
public class UsersService {

    private final IUsersRepo usersRepo;

    @Autowired
    public UsersService(IUsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

    public Iterable<Users> findAllUsers() {
        return usersRepo.findAll();
    }

    public Users saveUser(Users user) {
        //Validamos los datos
        if (validUserData(user)) {
            return usersRepo.save(user);
        }

        return null;
    }

    private boolean validUserData(Users user) {
        //Validamos que los datos no sean null
        return !(user.getFirstName() == null && user.getLastName() == null
                || user.getRole() == null
                || user.getEmail() == null && user.getUsername() == null
                || user.getPasswordHash() == null && user.getSalt() == null);
    }

}
