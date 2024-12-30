package com.grayhat.apicriptografia.UserServiceTest;

import com.grayhat.apicriptografia.BaseServiceTest;
import com.grayhat.apicriptografiausuarios.ApicriptografiausuariosApplication;
import com.grayhat.apicriptografiausuarios.model.Permissions;
import com.grayhat.apicriptografiausuarios.model.Roles;
import com.grayhat.apicriptografiausuarios.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApicriptografiausuariosApplication.class)
public class UsersServiceTest extends BaseServiceTest {

    @Test
    public void testCreateUserFlow(){
        //Primero creamos un nuevo permiso
        Permissions testPermission = createTestPermission();
        permissionsService.savePermissions(testPermission);

        //Creamos el rol de acceso asignando el permiso creado anteriormente
        Roles testRole = createTestRole(testPermission);
        rolesService.saveRoles(testRole);

        //Finalmente creamos el nuevo usuario, asignadole el rol de acceso creado anteriormente
        Users testUsers = createTestUser(testRole);
        userService.saveUser(testUsers);
    }

}
