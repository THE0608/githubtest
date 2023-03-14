package vo;

public class Book {
	private String barCode;
	private String bname;
	private double price;
	private String supply;
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getSupply() {
		return supply;
	}
	public void setSupply(String supply) {
		this.supply = supply;
	}
	public Book(String barCode, String bname, double price, String supply) {
		super();
		this.barCode = barCode;
		this.bname = bname;
		this.price = price;
		this.supply = supply;
	}
	public Book() {};
}
