package cl.oscar_pino.apiSecurityJWT.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cl.oscar_pino.apiSecurityJWT.entities.SaleDetailEntity;

@Repository
public interface ISaleDetailRepository extends CrudRepository<SaleDetailEntity, Long> {

}
