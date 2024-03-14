package com.grayhat.apicriptografiausuarios.repository;

import com.grayhat.apicriptografiausuarios.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author grayhat
 */
public interface IUsersRepo extends IGenericRepo<Users, Long> {

    @Query("select u from Users u where u.email = ?1")
    Optional<Users> findByEmail(String email);

    @Query("select u from Users u where u.username = ?1")
    Optional<Users> findByUsername(String username);

}
