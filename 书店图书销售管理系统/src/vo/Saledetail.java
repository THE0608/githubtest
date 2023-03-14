package vo;

public class Saledetail {
	private String Ish;//流水号
	private String barCode;//图书条形码
	private int count;//数量
	private String operator;//收银员
	private String saleTime;//销售时间（date time类型）
	public String getIsh() {
		return Ish;
	}
	public void setIsh(String ish) {
		Ish = ish;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getSaleTime() {
		return saleTime;
	}
	public void setSaleTime(String saleTime) {
		this.saleTime = saleTime;
	}
	public Saledetail () {}
	public Saledetail(String ish, String barCode, int count, String operator, String saleTime) {
		super();
		Ish = ish;
		this.barCode = barCode;
		this.count = count;
		this.operator = operator;
		this.saleTime = saleTime;
	}


}