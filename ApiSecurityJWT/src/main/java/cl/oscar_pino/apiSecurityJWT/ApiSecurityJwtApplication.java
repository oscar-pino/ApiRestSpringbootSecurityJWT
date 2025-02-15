package cl.oscar_pino.apiSecurityJWT;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.oscar_pino.apiSecurityJWT.entities.CustomerEntity;
import cl.oscar_pino.apiSecurityJWT.entities.EmployeeEntity;
import cl.oscar_pino.apiSecurityJWT.entities.PermissionEntity;
import cl.oscar_pino.apiSecurityJWT.entities.RoleEntity;
import cl.oscar_pino.apiSecurityJWT.entities.UserEntity;
import cl.oscar_pino.apiSecurityJWT.entities.enums.PermissionEnum;
import cl.oscar_pino.apiSecurityJWT.entities.enums.RoleEnum;
import cl.oscar_pino.apiSecurityJWT.services.CustomerServiceImp;
import cl.oscar_pino.apiSecurityJWT.services.EmployeeServiceImp;
import cl.oscar_pino.apiSecurityJWT.services.UserServiceImp;

@SpringBootApplication
public class ApiSecurityJwtApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImp userServiceImp;
	
	@Autowired
	private CustomerServiceImp customerServiceImp;
	
	@Autowired
	private EmployeeServiceImp employeeServiceImp;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiSecurityJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		createUsers();
		createCustormers();
		createEmployees();

	}

	private void createUsers() {

		if (userServiceImp.readAll().isEmpty()) {

			/* Create PERMISSIONS */
			PermissionEntity createPermission = new PermissionEntity(PermissionEnum.CREATE);
			PermissionEntity readPermission = new PermissionEntity(PermissionEnum.READ);
			PermissionEntity updatePermission = new PermissionEntity(PermissionEnum.UPDATE);
			PermissionEntity deletePermission = new PermissionEntity(PermissionEnum.DELETE);

			/* Create ROLES */
			RoleEntity adminRole = new RoleEntity(RoleEnum.ADMIN,
					Set.of(createPermission, readPermission, updatePermission, deletePermission));
			RoleEntity developerRole = new RoleEntity(RoleEnum.DEVELOPER,
					Set.of(createPermission, readPermission, updatePermission));
			RoleEntity inviteRole = new RoleEntity(RoleEnum.INVITED, Set.of(readPermission));
			RoleEntity userRole = new RoleEntity(RoleEnum.USER, Set.of(createPermission, readPermission));

			UserEntity oscar = new UserEntity("Oscar", passwordEncoder.encode("1234"), Set.of(adminRole));
			UserEntity luis = new UserEntity("Luis", passwordEncoder.encode("1234"), Set.of(developerRole));
			UserEntity jose = new UserEntity("Jose", passwordEncoder.encode("1234"), Set.of(inviteRole));
			UserEntity pedro = new UserEntity("Pedro", passwordEncoder.encode("1234"), Set.of(userRole));

			userServiceImp.createAll(Set.of(oscar, luis, jose, pedro));
		}

	}
	
	private void createCustormers() {
		
		if(customerServiceImp.readAll().isEmpty()) {

			String[] names = {"Julio", "Ana", "Felipe", "Gonzalo", "Ester"};
			String[] lastNames = {"Robles", "Garcia", "Tapia", "Mella", "Milla"};
			String[] phones = {"2315698", "5109258", "1782394", "4561923", "6712395"};
			String[] address = {"las flores 12", "colon 45", "vallenar 112", "ovalle 99", "margarita 2045"};
			
			for(int i=0;i<5;i++)
				customerServiceImp.create(new CustomerEntity(names[i], lastNames[i],names[i]+"@test.com", phones[i], address[i]));			
		}
		
	}
	
	private void createEmployees() {
		
		if(employeeServiceImp.readAll().isEmpty()) {
			
			String[] names = {"Joaquin", "Daniel", "Mario", "Claudio", "Miguel"};
			String[] lastNames = {"Sabinas", "Ortega", "Moreno", "Alvarez", "Concha"};
			String[] positions = {"relajantes", "annestesico", "proteinas", "cereal", "colorantes"};
			float[] salaries = new float[5];
			
			for(int j=0;j<5;j++)
				salaries[j] = 365_000f;
			
			for(int i=0;i<5;i++)
				employeeServiceImp.create(new EmployeeEntity(names[i], lastNames[i], positions[i], salaries[i]));			
		}
		
	}
}
