package cl.oscar_pino.apiSecurityJWT.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.oscar_pino.apiSecurityJWT.entities.RoleEntity;

@Repository
public interface IRoleRepository extends CrudRepository<RoleEntity, Long> {
	
	List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
	
}
