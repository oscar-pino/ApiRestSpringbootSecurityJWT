package cl.oscar_pino.apiSecurityJWT.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.oscar_pino.apiSecurityJWT.entities.CustomerEntity;
import cl.oscar_pino.apiSecurityJWT.repositories.ICustomerRepository;

@Service
public class CustomerServiceImp implements IDAO<CustomerEntity> {
	
	@Autowired
	private ICustomerRepository customerRepository;

	@Override
	public void create(CustomerEntity customer) {
		
		customerRepository.save(customer);		
	}
	
	public void createAll(Set<CustomerEntity> customers) {
		
		customerRepository.saveAll(customers);		
	}

	@Override
	public List<CustomerEntity> readAll() {
		
		return (List<CustomerEntity>)customerRepository.findAll();
	}

	@Override
	public Optional<CustomerEntity> readById(Long id) {
		
		return customerRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		
		customerRepository.deleteById(id);		
	}

	@Override
	public void update(CustomerEntity customer) {
		
		customerRepository.save(customer);
		
	}

}
