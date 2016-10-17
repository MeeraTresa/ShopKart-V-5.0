package pack;

public class Product {
	private String prodId;
	private String prodName;
	private float cost;
	private int inventory;
	public Product(){
		
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public Product(String prodId, String prodName, float cost, int inventory) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.cost = cost;
		this.inventory = inventory;
	}
	@Override
	public String toString() {
		return "Product [prodId=" + prodId + ", prodName=" + prodName + ", cost=" + cost + ", inventory=" + inventory
				+ "]";
	}
	
}
