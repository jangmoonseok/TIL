package hellojpa.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_order_item")
public class OrderItem {

	@Id
	@GeneratedValue
	@Column(name = "order_item_id")
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private Order order;
	
	@Column(name = "order_item_price")
	private int orderPrice;
	
	@Column(name = "order_item_count")
	private int count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
