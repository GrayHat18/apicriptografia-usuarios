package com.grayhat.apicriptografiausuarios.controller;

import com.grayhat.apicriptografiausuarios.dto.PermissionsDto;
import com.grayhat.apicriptografiausuarios.model.Permissions;
import com.grayhat.apicriptografiausuarios.service.PermissionsService;
import com.grayhat.apicriptografiausuarios.util.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author grayhat
 */
@RestController
@RequestMapping("/permissions")
public class PermissionsController {

    private final PermissionsService permissionsService;

    @Autowired
    public PermissionsController(PermissionsService permissionsService) {
        this.permissionsService = permissionsService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerPermissions(@RequestBody PermissionsDto permissionsDto) {
        try {
            PermissionsDto permissionDto = new PermissionsDto.Builder()
                    .descriptorName(permissionsDto.getDescriptorName())
                    .access(permissionsDto.getAccess())
                    .build();

            Permissions permissions = Mapper.mapDtoToEntity(permissionDto, Permissions.class);

            Permissions savedPermission = permissionsService.savePermissions(permissions);

            if (savedPermission != null) {
                return ResponseEntity.ok("Permisos Registrado Correctamente en Base de Datos");
            } else {
                return ResponseEntity.badRequest().body("Datos de Permisos son Inválidos");
            }

        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace(System.out);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Registrar un Permisos de Acceso");
        }
    }

}
