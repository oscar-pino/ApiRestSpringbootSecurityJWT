package cl.oscar_pino.apiSecurityJWT.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "el campo no debe ser null o solo contener espacios en blanco")
	@Size(min = 3, max = 20, message = "ingrese 3 caracteres como mínimo y 20 como máximo")
    private String username;
    
    @NotBlank(message = "el campo no debe ser null o solo contener espacios en blanco")
	@Size(min = 3, message = "ingrese 3 caracteres como mínimo")
    private String password;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles;
    
    public UserEntity(String username, String password, Set<RoleEntity> roles) {
    	
    	this.username = username;
    	this.password = password;
    	this.roles = roles;
    	this.accountNoExpired = true;
    	this.accountNoLocked = true;
    	this.credentialNoExpired = true;
    	this.isEnabled = true;
    }
}