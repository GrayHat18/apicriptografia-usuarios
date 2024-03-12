package com.grayhat.apicriptografiausuarios.util;

import com.grayhat.apicriptografiausuarios.dto.PermissionsDto;
import com.grayhat.apicriptografiausuarios.dto.RolesDto;
import com.grayhat.apicriptografiausuarios.model.Permissions;
import com.grayhat.apicriptografiausuarios.model.Roles;
import java.lang.reflect.Field;

/**
 * Clase para realizar Mapeos entre clases de Entidad a clases DTO,
 * considerandos aspectos de atributos exactamente iguales entre estas o
 * atributos con diferencias, por ejemplo atributos embebidos de otra clase
 * relacionada
 *
 * @author grayhat
 */
public class Mapper {

    /**
     *
     * @param <T> Clase ENTITY
     * @param <R> Clase DTO
     * @param entity nombre de la clase entidad
     * @param dtoClass clase de DTO a la cual se hará el mapeo
     * @return dto objeto de clase DTO
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T, R> R mapEntityToDto(T entity, Class<R> dtoClass) throws InstantiationException, IllegalAccessException {
        R dto = dtoClass.newInstance();

        for (Field dtoField : dtoClass.getDeclaredFields()) {
            dtoField.setAccessible(true);
            String fieldName = dtoField.getName();
            Field entityField;

            try {
                entityField = entity.getClass().getDeclaredField(fieldName);
                entityField.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e) {
                continue;
            }

            Object value = entityField.get(entity);

            //Validamos los casos de mapeo Permissions y Roles, para que coincidan los campos en el mapeo de entity a dto
            if (value instanceof Permissions && dtoField.getType().equals(PermissionsDto.class)) {
                Permissions permissions = (Permissions) value;
                PermissionsDto permissionsDto = convertPermissionsToDto(permissions);
                dtoField.set(dto, permissionsDto);
            } else if (value instanceof Roles && dtoField.getType().equals(RolesDto.class)) {
                Roles roles = (Roles) value;
                RolesDto rolesDto = convertRolesToDto(roles);
                dtoField.set(dto, rolesDto);
            } else {
                //En el caso de que sean exactamente iguales, se utilizar el valor value propiamente tal
                dtoField.set(dto, value);
            }

        }

        return dto;
    }

    /**
     * Metodo para Mapear una clase DTO a una clase Entidad
     *
     * @param <T> clase ENTITY
     * @param <R> clase DTO
     * @param dto objeto de la clase DTO
     * @param entityClass clase de entidad a la cual se hará el mapeo
     * @return entity objeto de clase ENTITY
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T, R> T mapDtoToEntity(R dto, Class<T> entityClass) throws InstantiationException, IllegalAccessException {
        T entity = entityClass.newInstance();

        for (Field entityField : entityClass.getDeclaredFields()) {
            entityField.setAccessible(true);
            String fieldName = entityField.getName();
            Field dtoField;

            try {
                dtoField = dto.getClass().getDeclaredField(fieldName);
                dtoField.setAccessible(true);
            } catch (NoSuchFieldException | SecurityException e) {
                continue;
            }

            Object value = dtoField.get(dto);

            //Validamos los casos de mapeo PermissionsDto y RolesDto, para que coincidan los campos en el mapeo de dto a entity
            //En el caso de que los campos entre dto y entity no sean exactamente iguales
            if (value instanceof PermissionsDto && entityField.getType().equals(Permissions.class)) {
                PermissionsDto permissionsDto = (PermissionsDto) value;
                Permissions permission = convertPermissionsDtoToEntity(permissionsDto);
                entityField.set(entity, permission);
            } else if (value instanceof RolesDto && entityField.getType().equals(Roles.class)) {
                RolesDto rolesDto = (RolesDto) value;
                Roles roles = convertRolesDtoToEntity(rolesDto);
                entityField.set(entity, roles);

            } else {
                //En el caso de que sena exactamente iguales, se utiliza el valor value propiamente tal
                entityField.set(entity, value);
            }
        }

        return entity;
    }

    /**
     * Metodo para la conversion de dto a entity para PermissionsDto/Permissions
     *
     * @param permissionsDto objeto de la clase PermissionsDTO
     * @return permissions objeto de la clase Permissions
     */
    private static Permissions convertPermissionsDtoToEntity(PermissionsDto permissionsDto) {
        Permissions permissions = new Permissions.Builder()
                .idPermission(permissionsDto.getIdPermission())
                .build();

        return permissions;
    }

    /**
     * Metodos para la conversión de dto a entity para RolesDto/Roles
     *
     * @param rolesDto objeto de la clase RolesDto
     * @return roles objeto de la clase Roles
     */
    private static Roles convertRolesDtoToEntity(RolesDto rolesDto) {
        Roles roles = new Roles.Builder()
                .idRole(rolesDto.getIdRole())
                .build();

        return roles;
    }

    /**
     * Metodo para la conversión de entity a dto para Permissions/PermissionsDto
     *
     * @param permissions objeto de la clase Permissions
     * @return objeto de la clase PermissionsDto
     */
    private static PermissionsDto convertPermissionsToDto(Permissions permissions) {
        PermissionsDto permissionsDto = new PermissionsDto.Builder()
                .idPermission(permissions.getIdPermission())
                .descriptorName(permissions.getDescriptorName())
                .access(permissions.getAccess())
                .createdAt(permissions.getCreatedAt())
                .updatedAt(permissions.getUpdatedAt())
                .enabled(permissions.isEnabled())
                .build();

        return permissionsDto;
    }

    /**
     * Metodo para la conversión de entity a DTO para Roles/RolesDto
     *
     * @param roles objeto de la clase Roles
     * @return rolesDto objeto de la clase RolesDto
     */
    private static RolesDto convertRolesToDto(Roles roles) {
        RolesDto rolesDto = new RolesDto.Builder()
                .idRole(roles.getIdRole())
                .permission(convertPermissionsToDto(roles.getPermission()))
                .descriptorName(roles.getDescriptorName())
                .createdAt(roles.getCreatedAt())
                .updatedAt(roles.getUpdatedAt())
                .enabled(roles.isEnabled())
                .build();

        return rolesDto;
    }

}
