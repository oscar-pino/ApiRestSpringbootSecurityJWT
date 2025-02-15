package cl.oscar_pino.apiSecurityJWT.entities;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Email(message = "ingrese un email valido")
	private String email;

	private String phone;
	
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SaleEntity.class, mappedBy = "customer")
	private Set<SaleEntity> sales;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = PrescriptionEntity.class, mappedBy = "customer")
	private Set<PrescriptionEntity> prescriptions;
	
	public CustomerEntity(String name, String lastName, String email, String phone, String address) {
		
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		
	}


}
