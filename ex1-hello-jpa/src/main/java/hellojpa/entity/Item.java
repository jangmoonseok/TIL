package hellojpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_item")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "item_division")
public class Item {

	@Id
	@GeneratedValue
	@Column(name = "item_id")
	private Long id;
	
	@Column(name = "item_name")
	private String name;
	
	@Column(name = "item_price")
	private int price;
	
	@Column(name = "item_stockQuantity")
	private int stockQuantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	
	
}
