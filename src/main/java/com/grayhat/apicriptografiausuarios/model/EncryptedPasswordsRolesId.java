package com.grayhat.apicriptografiausuarios.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author grayhat
 */
public class EncryptedPasswordsRolesId implements Serializable {

    private Long role;
    private Long encryptedPassword;

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.role);
        hash = 53 * hash + Objects.hashCode(this.encryptedPassword);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EncryptedPasswordsRolesId other = (EncryptedPasswordsRolesId) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return Objects.equals(this.encryptedPassword, other.encryptedPassword);
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(Long encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

}
