package com.grayhat.apicriptografiausuarios;

import com.grayhat.apicriptografiausuarios.dto.PermissionsDto;
import com.grayhat.apicriptografiausuarios.dto.EncryptedPasswordsRolesDto;
import com.grayhat.apicriptografiausuarios.dto.EncryptedPasswordsDto;
import com.grayhat.apicriptografiausuarios.dto.EncryptionsDto;
import com.grayhat.apicriptografiausuarios.dto.RolesDto;
import com.grayhat.apicriptografiausuarios.dto.UsersDto;
import com.grayhat.apicriptografiausuarios.model.Encryptions;
import com.grayhat.apicriptografiausuarios.model.EncryptedPasswords;
import com.grayhat.apicriptografiausuarios.model.Roles;
import com.grayhat.apicriptografiausuarios.model.Users;
import com.grayhat.apicriptografiausuarios.model.EncryptedPasswordsRoles;
import com.grayhat.apicriptografiausuarios.model.Permissions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author grayhat
 */
@Configuration
public class AppConfig {

    /*Bean para las clases de modelo con el patrón builder*/
    @Bean
    public Users.Builder usersBuilder() {
        return new Users.Builder();
    }

    @Bean
    public Roles.Builder rolesBuilder() {
        return new Roles.Builder();
    }

    @Bean
    public Permissions.Builder permissionsBuilder() {
        return new Permissions.Builder();
    }

    @Bean
    public Encryptions.Builder encryptionsBuilder() {
        return new Encryptions.Builder();
    }

    @Bean
    public EncryptedPasswordsRoles.Builder encryptedPasswordsRolesBuilder() {
        return new EncryptedPasswordsRoles.Builder();
    }

    @Bean
    public EncryptedPasswords.Builder encryptedPasswordsBuilder() {
        return new EncryptedPasswords.Builder();
    }

    /*--------------------------------------------------------*/

 /*Bean para las clases de dto que tienen el patrón builder*/
    @Bean
    public UsersDto.Builder usersDtoBuilder() {
        return new UsersDto.Builder();
    }

    @Bean
    public RolesDto.Builder rolesDtoBuilder() {
        return new RolesDto.Builder();
    }

    @Bean
    public PermissionsDto.Builder permissionsDtoBuilder() {
        return new PermissionsDto.Builder();
    }

    @Bean
    public EncryptionsDto.Builder encryptionsDtoBuilder() {
        return new EncryptionsDto.Builder();
    }

    @Bean
    public EncryptedPasswordsRolesDto.Builder encryptedPasswordsRolesDtoBuilder() {
        return new EncryptedPasswordsRolesDto.Builder();
    }

    @Bean
    public EncryptedPasswordsDto.Builder encryptedPasswordsDtoBuilder() {
        return new EncryptedPasswordsDto.Builder();
    }

    /*------------------------------------------------------------*/
    
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
