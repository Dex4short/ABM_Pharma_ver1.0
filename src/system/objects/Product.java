package system.objects;

import system.enumerators.ProductCondition;

public class Product {
	private int prod_id;
	private Item item;
	private Packaging packaging;
	private Pricing pricing;
	private Remarks remarks;
	private ProductCondition product_condition;
	
	public Product() {
		setProdId(-1);
		setItem(new Item());
		setPackaging(new Packaging());
		setPricing(new Pricing());
		setRemarks(new Remarks(-1));
		setProduct_condition(null);
	}
	public Product(int inv_id, Item item, Packaging packaging, Pricing pricing, Remarks remarks, ProductCondition product_condition) {
		setProdId(inv_id);
		setItem(item);
		setPackaging(packaging);
		setPricing(pricing);
		setRemarks(remarks);
		setProduct_condition(product_condition);
	}
	@Override
	public String toString() {
		String str = 
			"Product( inv_id:" + getProdId() + " )\n" +
			"\t" + getItem().toString() + "\n" +
			"\t" + getPackaging().toString() + "\n" +
			"\t" + getPricing().toString() + "\n"
		;
		
		if(getRemarks() != null) {
			str += "\t" + getRemarks().toString() + "\n";
		}else {
			str += "\tRemarks( n/a )\n" +
			"\tno remarks \n";
		}
		
		return str;
	}
	public void setProdId(int inv_id) {
		this.prod_id = inv_id;
	}
	public int getProdId() {
		return prod_id;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Item getItem() {
		return item;
	}
	public Packaging getPackaging() {
		return packaging;
	}
	public void setPackaging(Packaging packaging) {
		this.packaging = packaging;
	}
	public Pricing getPricing() {
		return pricing;
	}
	public void setPricing(Pricing pricing) {
		this.pricing = pricing;
	}
	public void setRemarks(Remarks remarks) {
		this.remarks = remarks;
	}
	public Remarks getRemarks() {
		return remarks;
	}
	public ProductCondition getProductCondition() {
		return product_condition;
	}
	public void setProduct_condition(ProductCondition product_condition) {
		this.product_condition = product_condition;
	}
}
