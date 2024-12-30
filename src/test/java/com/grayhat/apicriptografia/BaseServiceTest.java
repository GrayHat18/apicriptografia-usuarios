package com.grayhat.apicriptografia;

import com.grayhat.apicriptografiausuarios.model.Permissions;
import com.grayhat.apicriptografiausuarios.model.Roles;
import com.grayhat.apicriptografiausuarios.model.Users;
import com.grayhat.apicriptografiausuarios.service.PermissionsService;
import com.grayhat.apicriptografiausuarios.service.RolesService;
import com.grayhat.apicriptografiausuarios.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class BaseServiceTest {

    @Autowired
    protected UsersService userService;

    @Autowired
    protected RolesService rolesService;

    @Autowired
    protected PermissionsService permissionsService;

    /**
     * Crea un nuevo permiso de acceso de prueba
     * Este metodo debe ser ejecutado antes de crear roles y usuarios,
     * ya que estos ultimos dependen de los permisos de accesos
     * @return Objeto de PermissionDto
     */
    protected Permissions createTestPermission(){
        Permissions permission = new Permissions();

        permission.setDescriptorName("TestPermission");
        permission.setAccess("Full");

        return permission;
    }

    /**
     * Se crea un nuevo rol, en donde se da como parametro un objeto de permmissionDto creado anteriormente
     * @return Objeto de Roles
     */
    protected Roles createTestRole(Permissions Permissions){
        Roles role = new Roles();

        role.setPermission(Permissions);
        role.setDescriptorName("TestRole");

        return role;
    }

    /**
     * Finalmmente se crea un usuario nuevo,
     * en donde se le da como parametro de entrada un objeto de Roles creado anteriormente
     * @return Objeto de Users
     */
    protected Users createTestUser(Roles Roles){
        Users user = new Users();

        user.setRole(Roles);
        user.setFirstName("Fabian");
        user.setLastName("Carrasco");
        user.setEmail("fabian.hernan95@gmail.com");
        user.setUsername("grayhat18");
        user.setPasswordHash("pass123456");

        return user;
    }



}
