package kg.gulnaz.jpa.repository;

import kg.gulnaz.jpa.entity.RoleEntity;

public interface CustomRoleRepository {
    RoleEntity getDefaultUserRole();
}
