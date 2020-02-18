package portfolio.project.ecommerceWeb.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Basket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_gen")
	@SequenceGenerator(name = "basket_gen", sequenceName = "BASKET_SEQ", allocationSize = 1)
	private long basketID;
	
	@ManyToMany
	private List<Products> product;

	@Column
	private int quantityOrdered;
	
	@Column
	private LocalDate orderDate;

	@Column
	private double totalPrice;
	
	@OneToOne
	@JoinColumn(name = "user")
	private User user;

	public Basket() {
		super();
	}

	public Basket(List<Products> product, int quantityOrdered, LocalDate orderDate, double totalPrice, User user) {
		super();
		this.product = product;
		this.quantityOrdered = quantityOrdered;
		this.orderDate = orderDate;
		this.totalPrice = totalPrice;
		this.user = user;
	}

	public long getBasketID() {
		return basketID;
	}

	public void setBasketID(long basketID) {
		this.basketID = basketID;
	}

	public List<Products> getProducts() {
		return product;
	}

	public void setProduct(List<Products> product) {
		this.product = product;
	}

	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Basket [product=" + product + ", quantityOrdered=" + quantityOrdered + ", orderDate=" + orderDate
				+ ", totalPrice=" + totalPrice + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (basketID ^ (basketID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Basket other = (Basket) obj;
		if (basketID != other.basketID)
			return false;
		return true;
	}
	
	
	
	
}
