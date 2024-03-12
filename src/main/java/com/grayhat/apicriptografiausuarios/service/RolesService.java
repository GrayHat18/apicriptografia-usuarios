package com.grayhat.apicriptografiausuarios.service;

import com.grayhat.apicriptografiausuarios.model.Roles;
import com.grayhat.apicriptografiausuarios.repository.IRolesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author grayhat
 */
@Service
public class RolesService {

    private final IRolesRepo rolesrepo;

    @Autowired
    public RolesService(IRolesRepo rolesRepo) {
        this.rolesrepo = rolesRepo;
    }

    public Iterable<Roles> findAllRoles() {
        return rolesrepo.findAll();
    }

    public Roles saveRoles(Roles roles) {
        if (validRolesData(roles)) {
            return rolesrepo.save(roles);
        }

        return null;
    }

    private boolean validRolesData(Roles roles) {
        return roles.getPermission() != null && roles.getDescriptorName() != null;
    }

}
