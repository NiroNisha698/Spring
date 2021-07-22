package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long productID;
	
	@Column(name ="productName")
	private String productName;
	
	@Column(name ="productPrice")
	private float productPrice;
	
	@Column(name ="expiryDate")
	private String expiryDate;
	
	
	
	
	public Product(String productName, float productPrice) {
		super();
		this.productName = productName;
		this.productPrice = productPrice;
	}
	
	
	public Product() {
		super();
	}


	
	public long getProductID() {
		return productID;
	}


	public void setProductID(long productID) {
		this.productID = productID;
	}


	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	

}
