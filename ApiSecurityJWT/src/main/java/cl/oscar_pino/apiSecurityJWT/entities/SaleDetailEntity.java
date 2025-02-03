package cl.oscar_pino.apiSecurityJWT.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sale_detail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SaleDetailEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private int amount;
	
	@Column(name = "unit_price")
	private float unitPrice;
	
	@Column(name = "sub_total")
	private float subTotal;	
	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = SaleEntity.class)
	@JoinColumn(name = "sale_id")
	private SaleEntity sale;	
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = MedicineEntity.class)
	@JoinColumn(name = "medicine_id")
	private MedicineEntity medicine;

}
