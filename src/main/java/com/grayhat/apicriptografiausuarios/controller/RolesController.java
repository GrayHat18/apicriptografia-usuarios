package com.grayhat.apicriptografiausuarios.controller;

import com.grayhat.apicriptografiausuarios.dto.RolesDto;
import com.grayhat.apicriptografiausuarios.model.Roles;
import com.grayhat.apicriptografiausuarios.service.RolesService;
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
@RequestMapping("/roles")
public class RolesController {

    private final RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerRoles(@RequestBody RolesDto rolesDto) {
        try {
            RolesDto roleDto = new RolesDto.Builder()
                    .permission(rolesDto.getPermission())
                    .descriptorName(rolesDto.getDescriptorName())
                    .build();

            Roles roles = Mapper.mapDtoToEntity(roleDto, Roles.class);

            Roles savedRoles = rolesService.saveRoles(roles);

            if (savedRoles != null) {
                return ResponseEntity.ok("Rol de Acceso Registrado Correctamente en Base de Datos");
            } else {
                return ResponseEntity.badRequest().body("Datos de Roles son Inválidos");
            }

        } catch (IllegalAccessException | InstantiationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al Registrar Roles de Acceso");
        }
    }

}
