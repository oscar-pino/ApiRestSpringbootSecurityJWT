package cl.oscar_pino.apiSecurityJWT.entities;

import cl.oscar_pino.apiSecurityJWT.entities.enums.PermissionEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "permissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "permission_name")
    @Enumerated(EnumType.STRING)  
    @NotNull(message = "el campo no debe ser null")
    private PermissionEnum permissionEnum;	
    
    public PermissionEntity(PermissionEnum permissionEnum) {
    	
    	this.permissionEnum = permissionEnum;
    }
}
