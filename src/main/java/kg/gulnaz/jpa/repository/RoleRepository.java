package kg.gulnaz.jpa.repository;

import kg.gulnaz.jpa.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleEntity, String>, CustomRoleRepository {
}
