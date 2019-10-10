class step010 {

    private int shopNo;
	private String shopName;
	private String shopLocation;
	private String shopStatus;
	
	public int getShopNo() {
		return shopNo;
	}
	public void setShopNo(int shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopLocation() {
		return shopLocation;
	}
	public void setShopLocation(String shopLocation) {
		this.shopLocation = shopLocation;
	}
	public String getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}
	public Shop() {
		super();
	}
	public Shop(int shopNo, String shopName, String shopLocation, String shopStatus) {
		super();
		this.shopNo = shopNo;
		this.shopName = shopName;
		this.shopLocation = shopLocation;
		this.shopStatus = shopStatus;
	}
}