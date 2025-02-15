package cl.oscar_pino.apiSecurityJWT.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.oscar_pino.apiSecurityJWT.entities.SaleEntity;

@Repository
public interface ISaleRepository extends CrudRepository<SaleEntity, Long> {

}
