
public class SP2024_Product_Jung {
	float[] price = new float[4];
	int[] productCount=new int[4];
	
	public SP2024_Product_Jung() {}
	public SP2024_Product_Jung(int[] unitModel,float[] unitPrice) {
		for(int i=0;i<unitModel.length;i++) {
			productCount[i]=unitModel[i];	
		}
		price= unitPrice;
	}
	
	private float getPrice(int idx) {
		return price[idx];
	}
	private float getSubTotalPerItem(int idx) {
		return getPrice(idx)* productCount[idx];
	}
	private float getSubTotal() {
		float subTotal=0.0f;
		for(int i=0;i<4;i++) {
			subTotal += getSubTotalPerItem(i);
		}
		return subTotal;
	}
	private float getTax() {
		return getSubTotal()*SP2024_SaleProductApplication_Jung.TAX;
	}
	private float getTotal() {
		return getSubTotal()+getTax();
	}
	public String toString() {
		return productCount[0]+" "+productCount[1]+" "+productCount[2]+" "+productCount[3];
	}
	public String printReceipt() {
		String builder="";
		builder+=("-------------------------------------\n");
		for(int i=0;i<4;i++) {
			String modelName="MODEL A";
			if(i==1) modelName="MODEL B";
			else if(i==2) modelName="MODEL C";
			else if(i==3) modelName="MODEL D";
			builder +=(String.format(modelName+" (%5.2f) %5d %10.2f\n",price[i],productCount[i], getSubTotalPerItem(i)));
		}	
		
		builder+=("-------------------------------------\n");
		builder+=(String.format("SubTotal: %22.2f\n", getSubTotal()));
		builder+=(String.format("Tax: %27.2f\n", getTax()));
		builder+=(String.format("Total: %25.2f\n", getTotal()));
		return builder;
	}
}
