package kg.gulnaz.jpa.repository;

import kg.gulnaz.jpa.entity.RoleEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomRoleRepositoryImpl implements CustomRoleRepository {
    private static final String DEFAULT_USER_ROLE_NAME = "USER";
    @PersistenceContext
    private EntityManager em;

    @Override
    public RoleEntity getDefaultUserRole() {
        RoleEntity entity = em.find(RoleEntity.class, DEFAULT_USER_ROLE_NAME);
        if (entity == null) {
            entity = new RoleEntity(DEFAULT_USER_ROLE_NAME);
            em.persist(entity);
            return entity;
        }
        return entity;
    }
}
