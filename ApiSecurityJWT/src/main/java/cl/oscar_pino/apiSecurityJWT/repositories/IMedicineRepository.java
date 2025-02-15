package cl.oscar_pino.apiSecurityJWT.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.oscar_pino.apiSecurityJWT.entities.MedicineEntity;

@Repository
public interface IMedicineRepository extends CrudRepository<MedicineEntity, Long> {

}
