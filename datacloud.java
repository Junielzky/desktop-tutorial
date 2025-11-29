package FinalOutPut;

import java.io.BufferedReader;

public class datacloud {
	private static int[] barcodes = new int[10] ;
	private static String[] items =  new String[10];
		//{"Keyboard", "Mouse", "Monitor","Pc_case","Mother_Board","Head_Phone","Web_Camera","Speaker","CPU","AVR"};
	private float[] prices = new float[10];
		//{250.00f, 350.00f, 4599.00f, 2599.00f, 2899.00f, 2000.00f, 1295.00f, 999.00f, 7999.00f, 1500.00f};
	private String item = "";
	private float price = 0.00f;
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
	public boolean addProduct (int index, int barcode, String itemName, float itemPrice) {
		if(index < 0 || index >= barcodes.length) {
			return false;
		}
		barcodes[index] = barcode;
		items[index] = itemName;
		prices[index] = itemPrice;
		return true;
		
	}
	public void SearchItem(String barcode)
	{
		for (int i = 0; i < barcodes.length; i++)
		{
		if (barcode == barcodes[i]) {
				item = items[i];
				price = prices[i];	
				available = true;
				}
		}
		item ="";
		price = 0.00f;
	}
	public Object[][] getALLProducts(){
		Object [][] data = new Object[10][3];
		for (int i = 0; i < 10; i++) {
		data[i][0] = barcodes[i];
		data[i][0] = items[i];
		data[i][0] = prices[i];
		}
		return data;
	}
	
	public boolean IsFull(int currentIndex) {
		return currentIndex >= barcodes.length;
	}
	
	public boolean isEmpty() {
		for (int i= 0; i  < item.length; i++) {
			if (items[i] != null && !items[i].isEmpty()) {
				return false;
			}
		}
		return true;
	}
	public void saveProductToFile() {
		try (PrintWriter pw = PrintWriter(new FileWriter("inventory")))
		{
			for (int i = 0; i < barcodes.length; i++);
			{
				if(items[i] != null && !items[i].isEmpty()) 
				{
					pw.println(barcodes[i] + "," + items[i] + "," + prices[i]);
				}
			}
		catch (IDException e) 
		{
			e.printStackTrace();
		}
		}
	}
	public void LoadProductsFromFile() {
		try(BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))){
			String line;
			int index = 0;
			while ((line = br.readLine()) != null && index <10) {
				String[]parts = line.split(",");
				barcodes[index] = Integer.parseInt(parts[0]);
				items[index] = parts[1];
				prices[index] = Float.parseFloat(parts[3]);
				index++;
			}		
		}catch (IDExecption e) {
			
		}
	}
	public void clearInventory() {
		for (int i = 0; i < barcodes.length; i++) {
			barcodes[1] = 0;
			items[1] = "";
			prices[1] = 0.00f;
		}
		saveProductToFile();
	}
	
	private void clearSlot(int i) {
		barcodes[1] = 0;
		items[1] = "";
		prices[1] = 0.00f;

	}
}
