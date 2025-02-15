package cl.oscar_pino.apiSecurityJWT.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.oscar_pino.apiSecurityJWT.entities.RoleEntity;
import cl.oscar_pino.apiSecurityJWT.repositories.IRoleRepository;

@Service
public class RoleServiceImp implements IDAO<RoleEntity> {

	@Autowired
	IRoleRepository roleRepository;

	@Override
	public void create(RoleEntity role) {

		roleRepository.save(role);
	}

	@Override
	public List<RoleEntity> readAll() {

		return (List<RoleEntity>) roleRepository.findAll();
	}
	
	public List<RoleEntity> readAllRoleByName(List<String> names) {

		return (List<RoleEntity>) roleRepository.findRoleEntitiesByRoleEnumIn(names);
	}
	
	@Override
	public Optional<RoleEntity> readById(Long id) {

		return roleRepository.findById(id);
	}

	@Override
	public void update(RoleEntity roleEntity) {

		roleRepository.save(roleEntity);
	}

	@Override
	public void deleteById(Long id) {

		roleRepository.deleteById(id);
	}	

}
