package cl.oscar_pino.apiSecurityJWT.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.oscar_pino.apiSecurityJWT.entities.EmployeeEntity;
import cl.oscar_pino.apiSecurityJWT.repositories.IEmployeeRepository;

@Service
public class EmployeeServiceImp implements IDAO<EmployeeEntity> {
	
	@Autowired
	private IEmployeeRepository employeeRepository;

	@Override
	public void create(EmployeeEntity employeeEntity) {
		
		employeeRepository.save(employeeEntity);		
	}

	@Override
	public List<EmployeeEntity> readAll() {
		
		return (List<EmployeeEntity>)employeeRepository.findAll();
	}

	@Override
	public Optional<EmployeeEntity> readById(Long id) {
		
		return employeeRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		
		employeeRepository.deleteById(id);
	}

	@Override
	public void update(EmployeeEntity employeeEntity) {
		
		employeeRepository.save(employeeEntity);
		
	}

}
