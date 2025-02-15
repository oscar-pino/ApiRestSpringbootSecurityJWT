package cl.oscar_pino.apiSecurityJWT.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.oscar_pino.apiSecurityJWT.entities.RoleEntity;
import cl.oscar_pino.apiSecurityJWT.entities.UserEntity;
import cl.oscar_pino.apiSecurityJWT.entities.dto.AuthCreateUserRequest;
import cl.oscar_pino.apiSecurityJWT.entities.dto.AuthLoginRequest;
import cl.oscar_pino.apiSecurityJWT.entities.dto.AuthResponse;
import cl.oscar_pino.apiSecurityJWT.repositories.IRoleRepository;
import cl.oscar_pino.apiSecurityJWT.repositories.IUserRepository;
import cl.oscar_pino.apiSecurityJWT.utils.JwtUtils;
import jakarta.validation.constraints.Size;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {

		UserEntity userEntity = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));

		List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

		// agregamos los roles del usuario logueado en authorityList
		userEntity.getRoles().forEach(
				role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

		// agregamos los permisos del usuario logueado en authorityList
		userEntity.getRoles().stream().flatMap(role -> role.getPermissionList().stream()).forEach(
				permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionEnum().name())));

		return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.isEnabled(),
				userEntity.isAccountNoExpired(), userEntity.isCredentialNoExpired(), userEntity.isAccountNoLocked(),
				authorityList);
	}

	public AuthResponse createUser(AuthCreateUserRequest createRoleRequest) {

		String username = createRoleRequest.username();
		String password = createRoleRequest.password();
		List<String> rolesRequest = createRoleRequest.roleRequest().roleListName();

		Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest).stream()
				.collect(Collectors.toSet());

		if (roleEntityList.isEmpty()) {
			throw new IllegalArgumentException("The roles specified does not exist.");
		}

		UserEntity userEntity = UserEntity.builder().username(username).password(passwordEncoder.encode(password))
				.roles(roleEntityList).isEnabled(true).accountNoLocked(true).accountNoExpired(true)
				.credentialNoExpired(true).build();

		UserEntity userSaved = userRepository.save(userEntity);

		ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();

		userSaved.getRoles().forEach(
				role -> authorities.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

		userSaved.getRoles().stream().flatMap(role -> role.getPermissionList().stream()).forEach(
				permission -> authorities.add(new SimpleGrantedAuthority(permission.getPermissionEnum().name())));

		//SecurityContext securityContextHolder = SecurityContextHolder.getContext();
		Authentication authentication = new UsernamePasswordAuthenticationToken(userSaved, null, authorities);

		String accessToken = jwtUtils.createToken(authentication);

		AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, true);
		return authResponse;
	}

	public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {

		String username = authLoginRequest.username();
		String password = authLoginRequest.password();

		Authentication authentication = this.authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String accessToken = jwtUtils.createToken(authentication);
		AuthResponse authResponse = new AuthResponse(username, "User loged succesfully", accessToken, true);
		return authResponse;
	}

	public Authentication authenticate(String username, String password) {
		UserDetails userDetails = this.loadUserByUsername(username);

		if (userDetails == null) {
			throw new BadCredentialsException(String.format("Invalid username or password"));
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Incorrect Password");
		}

		return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
	}
}
