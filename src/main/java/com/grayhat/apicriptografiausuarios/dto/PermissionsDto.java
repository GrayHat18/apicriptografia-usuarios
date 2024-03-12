package com.grayhat.apicriptografiausuarios.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author grayhat
 */
public class PermissionsDto {

    @JsonProperty("idPermission")
    private int idPermission;

    @JsonProperty("descriptorName")
    private String descriptorName;

    @JsonProperty("access")
    private String access;

    @JsonProperty("createdAt")
    private Timestamp createdAt;

    @JsonProperty("updatedAt")
    private Timestamp updatedAt;

    @JsonProperty("enabled")
    private boolean enabled;

    public PermissionsDto() {

    }

    public PermissionsDto(Builder builder) {
        this.idPermission = builder.idPermission;
        this.descriptorName = builder.descriptorName;
        this.access = builder.access;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.enabled = builder.enabled;
    }

    public static class Builder {

        private int idPermission;
        private String descriptorName;
        private String access;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private boolean enabled;

        public Builder idPermission(int idPermission) {
            this.idPermission = idPermission;
            return this;
        }

        public Builder descriptorName(String descriptorName) {
            this.descriptorName = descriptorName;
            return this;
        }

        public Builder access(String access) {
            this.access = access;
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

        public PermissionsDto build() {
            return new PermissionsDto(this);
        }

    }

    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }

    public String getDescriptorName() {
        return descriptorName;
    }

    public void setDescriptorName(String descriptorName) {
        this.descriptorName = descriptorName;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
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
        hash = 37 * hash + this.idPermission;
        hash = 37 * hash + Objects.hashCode(this.descriptorName);
        hash = 37 * hash + Objects.hashCode(this.access);
        hash = 37 * hash + Objects.hashCode(this.createdAt);
        hash = 37 * hash + Objects.hashCode(this.updatedAt);
        hash = 37 * hash + (this.enabled ? 1 : 0);
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
        final PermissionsDto other = (PermissionsDto) obj;
        if (this.idPermission != other.idPermission) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.descriptorName, other.descriptorName)) {
            return false;
        }
        if (!Objects.equals(this.access, other.access)) {
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
        sb.append("PermissionsDto{");
        sb.append("idPermission=").append(idPermission);
        sb.append(", descriptorName=").append(descriptorName);
        sb.append(", access=").append(access);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", enabled=").append(enabled);
        sb.append('}');
        return sb.toString();
    }

}
