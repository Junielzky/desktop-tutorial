package FinalOutPut;

public class datacloud {
	private static String [] barcodes = new String[10] ;
	
	private static String[] items =  new String[10];
		//{"Keyboard", "Mouse", "Monitor","Pc_case","Mother_Board","Head_Phone","Web_Camera","Speaker","CPU","AVR"};
	
	private float[] prices = new float[10];
		//{250.00f, 350.00f, 4599.00f, 2599.00f, 2899.00f, 2000.00f, 1295.00f, 999.00f, 7999.00f, 1500.00f};

	private String item;
	private float price;
	private boolean available = false;
	
	
	public String getItem()
	{
		return item;
	}
	public float getPrice()
	{
		return price;
	}
	public boolean Available() 
	{
	return available;
	}
	
	/*public boolean addProduct (int index, String barcode, String itemName, float itemPrice) {
		if(index < 0 || index >= barcodes.length) {
			return false;
		}
		barcodes[index] = barcode;
		items[index] = itemName;
		prices[index] = itemPrice;
		
	}
	
	*/
	
	public void SearchItem(String barcode)
	
	
	{
		for (int i=0; i<barcodes.length; i++)
		{
		if (barcode.equalsIgnoreCase(barcodes[i]))
		{
	item = items[i];
	price = prices[i];	
	available = true;
	}
		}
	}

}
