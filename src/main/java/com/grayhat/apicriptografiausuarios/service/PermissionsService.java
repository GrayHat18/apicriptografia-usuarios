package com.grayhat.apicriptografiausuarios.service;

import com.grayhat.apicriptografiausuarios.model.Permissions;
import com.grayhat.apicriptografiausuarios.repository.IPermissionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author grayhat
 */
@Service
public class PermissionsService {

    private final IPermissionsRepo permissionsRepo;

    @Autowired
    public PermissionsService(IPermissionsRepo permissionsRepo) {
        this.permissionsRepo = permissionsRepo;
    }

    public Iterable<Permissions> findAllPermissions() {
        return permissionsRepo.findAll();
    }

    public Permissions savePermissions(Permissions permissions) {
        if (validPermissionsData(permissions)) {
            return permissionsRepo.save(permissions);
        }
        
        return null;
    }

    private boolean validPermissionsData(Permissions permissions) {
        return !(permissions.getDescriptorName() == null
                || permissions.getAccess() == null);
    }

}
