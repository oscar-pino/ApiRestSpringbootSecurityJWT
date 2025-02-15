package cl.oscar_pino.apiSecurityJWT.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "last_name")
	private String lastName;
	
	private String position;
	
	@Column(name = "hiring_date")
	private Date hiringDate;
	
	private float salary;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SaleEntity.class, mappedBy = "employee")
	private Set<SaleEntity> sales;
	
	public EmployeeEntity(String name, String lastName, String position, float salary) {
		
		this.name = name;
		this.lastName = lastName;
		this.position = position;
		this.salary = salary;		
	}
	
	@PrePersist
    public void prePersist() {
		
		Random rd = new Random();
		Long newLong = 2_592_100_000l;
		
        this.hiringDate = new Date(System.currentTimeMillis()-rd.nextLong(newLong));
    }	
}
