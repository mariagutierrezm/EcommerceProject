package portfolio.project.ecommerceWeb.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_gen")
	@SequenceGenerator(name = "products_gen", sequenceName = "PRODUCTS_SEQ", allocationSize = 1)
	private long productID;

	@Column(nullable = false, length = 100)
	private String titleProduct;

	@Column(length = 7)
	private BigDecimal weight;
	
	@Column(nullable = false, length = 5)
	private double price;

	@Column(nullable = false, length = 30)
	private String countryOfOrigin;

	@Column(nullable = false, length = 1240)
	private String description;

	@Column(nullable = true, length = 10)
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private TypeOfProduct typeOfProduct;
	
	@Column(nullable = true, columnDefinition="int(10) default '0'")
	private int quantityBasket;

	public Products() {
		super();
	}

	public Products(String titleProduct, BigDecimal weight, double price, String countryOfOrigin, String description,
			int quantity, TypeOfProduct typeOfProduct) {
		super();
		this.titleProduct = titleProduct;
		this.weight = weight;
		this.price = price;
		this.countryOfOrigin = countryOfOrigin;
		this.description = description;
		this.quantity = quantity;
		this.typeOfProduct = typeOfProduct;
	}

	public long getProductID() {
		return productID;
	}

	public void setProductID(long productID) {
		this.productID = productID;
	}

	public String getTitleProduct() {
		return titleProduct;
	}

	public void setTitleProduct(String titleProduct) {
		this.titleProduct = titleProduct;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public TypeOfProduct getTypeOfProduct() {
		return typeOfProduct;
	}

	public void setTypeOfProduct(TypeOfProduct typeOfProduct) {
		this.typeOfProduct = typeOfProduct;
	}

	public int getQuantityBasket() {
		return quantityBasket;
	}

	public void setQuantityBasket(int quantityBasket) {
		this.quantityBasket = quantityBasket;
	}
	
	@Override
	public String toString() {
		return "Products [titleProduct=" + titleProduct + ", weight=" + weight + ", price=" + price
				+ ", countryOfOrigin=" + countryOfOrigin + ", description=" + description + ", quantity=" + quantity
				+ ", typeOfProduct=" + typeOfProduct + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (productID ^ (productID >>> 32));
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
		Products other = (Products) obj;
		if (productID != other.productID)
			return false;
		return true;
	}


}
