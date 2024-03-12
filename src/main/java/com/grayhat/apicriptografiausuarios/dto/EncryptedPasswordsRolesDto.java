package com.grayhat.apicriptografiausuarios.dto;

import java.util.Objects;

/**
 *
 * @author grayhat
 */
public class EncryptedPasswordsRolesDto {

    private RolesDto role;
    private EncryptedPasswordsDto encryptedPassword;

    public EncryptedPasswordsRolesDto(Builder builder) {
        this.role = builder.role;
        this.encryptedPassword = builder.encryptedPassword;
    }

    public static class Builder {

        private RolesDto role;
        private EncryptedPasswordsDto encryptedPassword;

        public Builder role(RolesDto role) {
            this.role = role;
            return this;
        }

        public Builder encryptedPassword(EncryptedPasswordsDto encryptedPassword) {
            this.encryptedPassword = encryptedPassword;
            return this;
        }

        public EncryptedPasswordsRolesDto build() {
            return new EncryptedPasswordsRolesDto(this);
        }
    }

    public RolesDto getRole() {
        return role;
    }

    public void setRole(RolesDto role) {
        this.role = role;
    }

    public EncryptedPasswordsDto getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(EncryptedPasswordsDto encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.role);
        hash = 79 * hash + Objects.hashCode(this.encryptedPassword);
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
        final EncryptedPasswordsRolesDto other = (EncryptedPasswordsRolesDto) obj;
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        return Objects.equals(this.encryptedPassword, other.encryptedPassword);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EncryptedPasswordsRolesDto{");
        sb.append("role=").append(role);
        sb.append(", encryptedPassword=").append(encryptedPassword);
        sb.append('}');
        return sb.toString();
    }

}
