package cl.oscar_pino.apiSecurityJWT;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import cl.oscar_pino.apiSecurityJWT.entities.PermissionEntity;
import cl.oscar_pino.apiSecurityJWT.entities.RoleEntity;
import cl.oscar_pino.apiSecurityJWT.entities.UserEntity;
import cl.oscar_pino.apiSecurityJWT.entities.enums.PermissionEnum;
import cl.oscar_pino.apiSecurityJWT.entities.enums.RoleEnum;
import cl.oscar_pino.apiSecurityJWT.services.UserServiceImp;

@SpringBootApplication
public class ApiSecurityJwtApplication implements CommandLineRunner {

	@Autowired
	private UserServiceImp userServiceImp;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ApiSecurityJwtApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {		

			createUsers();

	}

	private void createUsers() {	
		
		if(userServiceImp.readAll().isEmpty()) {

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

			UserEntity oscar = new UserEntity("oscar", passwordEncoder.encode("1234"), Set.of(adminRole));
			UserEntity luis = new UserEntity("luis", passwordEncoder.encode("1234"), Set.of(developerRole));
			UserEntity jose = new UserEntity("jose", passwordEncoder.encode("1234"), Set.of(inviteRole));
			UserEntity pedro = new UserEntity("pedro", passwordEncoder.encode("1234"), Set.of(userRole));

			userServiceImp.createAll(Set.of(oscar, luis, jose, pedro));
	}
		
	}
}
