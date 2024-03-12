package com.grayhat.apicriptografiausuarios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author grayhat
 */
public class RolesDto {

    @JsonProperty("idRole")
    private int idRole;

    @JsonProperty("permission")
    private PermissionsDto permission;

    @JsonProperty("descriptorName")
    private String descriptorName;

    @JsonProperty("createdAt")
    private Timestamp createdAt;

    @JsonProperty("updatedAt")
    private Timestamp updatedAt;

    @JsonProperty("enabled")
    private boolean enabled;

    public RolesDto() {

    }

    public RolesDto(Builder builder) {
        this.idRole = builder.idRole;
        this.permission = builder.permission;
        this.descriptorName = builder.descriptorName;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.enabled = builder.enabled;
    }

    public static class Builder {

        private int idRole;
        private PermissionsDto permission;
        private String descriptorName;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private boolean enabled;

        public Builder idRole(int idRole) {
            this.idRole = idRole;
            return this;
        }

        public Builder permission(PermissionsDto permission) {
            this.permission = permission;
            return this;
        }

        public Builder descriptorName(String descriptorName) {
            this.descriptorName = descriptorName;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(Timestamp updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public RolesDto build() {
            return new RolesDto(this);
        }
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public PermissionsDto getPermission() {
        return permission;
    }

    public void setPermission(PermissionsDto permission) {
        this.permission = permission;
    }

    public String getDescriptorName() {
        return descriptorName;
    }

    public void setDescriptorName(String descriptorName) {
        this.descriptorName = descriptorName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + this.idRole;
        hash = 13 * hash + Objects.hashCode(this.permission);
        hash = 13 * hash + Objects.hashCode(this.descriptorName);
        hash = 13 * hash + Objects.hashCode(this.createdAt);
        hash = 13 * hash + Objects.hashCode(this.updatedAt);
        hash = 13 * hash + (this.enabled ? 1 : 0);
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
        final RolesDto other = (RolesDto) obj;
        if (this.idRole != other.idRole) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.descriptorName, other.descriptorName)) {
            return false;
        }
        if (!Objects.equals(this.permission, other.permission)) {
            return false;
        }
        if (!Objects.equals(this.createdAt, other.createdAt)) {
            return false;
        }
        return Objects.equals(this.updatedAt, other.updatedAt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RolesDto{");
        sb.append("idRole=").append(idRole);
        sb.append(", permission=").append(permission);
        sb.append(", descriptorName=").append(descriptorName);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", enabled=").append(enabled);
        sb.append('}');
        return sb.toString();
    }

}
