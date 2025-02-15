package cl.oscar_pino.apiSecurityJWT.entities;

import java.util.Date;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "medicines")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String description;
	
	private float price;
	
	private int stock;
	
	@Column(name = "expiration_date")
	@Temporal(TemporalType.DATE)
	@Future(message = "la fecha debe ser posterior a la fecha ingresada")
	@CreatedDate
	private Date expirationDate;
	
	@PrePersist
    public void prePersist() {
        
        if (expirationDate == null) {
        	expirationDate = new Date(System.currentTimeMillis()+(long)(3600000*30));
        }
    }
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SaleDetailEntity.class, mappedBy = "medicine")
	private Set<SaleDetailEntity> saleDetail;	
	
	@ManyToOne(targetEntity = SupplierEntity.class) 
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;
	
}
