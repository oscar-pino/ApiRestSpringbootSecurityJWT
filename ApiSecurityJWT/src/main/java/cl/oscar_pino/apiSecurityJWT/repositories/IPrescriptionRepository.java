package cl.oscar_pino.apiSecurityJWT.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.oscar_pino.apiSecurityJWT.entities.PrescriptionEntity;

@Repository
public interface IPrescriptionRepository extends CrudRepository<PrescriptionEntity, Long> {

}
