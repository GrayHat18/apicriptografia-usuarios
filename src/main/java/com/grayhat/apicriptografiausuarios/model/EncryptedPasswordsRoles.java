package com.grayhat.apicriptografiausuarios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author grayhat
 */
@Entity
@Table(name = "encrypted_passwords_roles")
@IdClass(EncryptedPasswordsRolesId.class)
public class EncryptedPasswordsRoles {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_role")
    private Roles role;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_encrypted_password")
    private EncryptedPasswords encryptedPassword;
    
    public EncryptedPasswordsRoles(){
        
    }

    public EncryptedPasswordsRoles(Builder builder) {
        this.role = builder.role;
        this.encryptedPassword = builder.encryptedPassword;
    }

    public static class Builder {

        private Roles role;
        private EncryptedPasswords encryptedPassword;

        public Builder role(Roles role) {
            this.role = role;
            return this;
        }

        public Builder encryptedPassword(EncryptedPasswords encryptedPassword) {
            this.encryptedPassword = encryptedPassword;
            return this;
        }

        public EncryptedPasswordsRoles build() {
            return new EncryptedPasswordsRoles(this);
        }
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public EncryptedPasswords getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(EncryptedPasswords encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
