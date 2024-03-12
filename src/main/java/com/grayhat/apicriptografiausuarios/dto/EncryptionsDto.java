package com.grayhat.apicriptografiausuarios.dto;

import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author grayhat
 */
public class EncryptionsDto {

    private int idEncryption;
    private String encryptedText;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private boolean enabled;

    public EncryptionsDto(Builder builder) {
        this.idEncryption = builder.idEncryption;
        this.encryptedText = builder.encryptedText;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.enabled = builder.enabled;
    }

    public static class Builder {

        private int idEncryption;
        private String encryptedText;
        private Timestamp createdAt;
        private Timestamp updatedAt;
        private boolean enabled;

        public Builder idEncryptions(int idEncryptions) {
            this.idEncryption = idEncryptions;
            return this;
        }

        public Builder encryptedText(String encryptedText) {
            this.encryptedText = encryptedText;
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

        public EncryptionsDto build() {
            return new EncryptionsDto(this);
        }

    }

    public int getIdEncryption() {
        return idEncryption;
    }

    public void setIdEncryption(int idEncryption) {
        this.idEncryption = idEncryption;
    }

    public String getEncryptedText() {
        return encryptedText;
    }

    public void setEncryptedText(String encryptedText) {
        this.encryptedText = encryptedText;
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
        hash = 37 * hash + this.idEncryption;
        hash = 37 * hash + Objects.hashCode(this.encryptedText);
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
        final EncryptionsDto other = (EncryptionsDto) obj;
        if (this.idEncryption != other.idEncryption) {
            return false;
        }
        if (this.enabled != other.enabled) {
            return false;
        }
        if (!Objects.equals(this.encryptedText, other.encryptedText)) {
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
        sb.append("EncryptionsDto{");
        sb.append("idEncryption=").append(idEncryption);
        sb.append(", encryptedText=").append(encryptedText);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append(", enabled=").append(enabled);
        sb.append('}');
        return sb.toString();
    }

}
