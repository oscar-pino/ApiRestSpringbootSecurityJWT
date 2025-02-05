package cl.oscar_pino.apiSecurityJWT.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import cl.oscar_pino.apiSecurityJWT.entities.UserEntity;

public interface IUserRepository extends CrudRepository<UserEntity, Long> {
	
	Optional<UserEntity> findByUsername(@Param(value = "name") String username);

}
