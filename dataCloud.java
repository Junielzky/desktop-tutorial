import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class dataCloud {
    private static int[] barcodes = new int[10];
    private static String[] items = new String[10];
    private static float[] prices = new float[10];

    private String item = "";
    private float price = 0.00f;

    // Getter methods
    public String getItem() {
        return item;
    }

    public float getPrice() {
        return price;
    }
    
    public boolean Product(int index, int barcode, String itemName, float itemPrice) {
    	if (index < 0 || index >= barcodes.length) {
    		return false;
    	}
    	
    	barcodes[index] = barcode;
        items[index] = itemName;
        prices[index] = itemPrice;
        return true;
    }
    
    public void searchItem(int barcode) {
        for (int i = 0; i < barcodes.length; i++) {
            if (barcode == barcodes[i]) {
                item = items[i];
                price = prices[i];
                return;
            }
        }
        
        item = "";
        price = 0.00f;
    }
    
    public Object[][] getAllProducts() {
    	Object[][] data = new Object[10][3];
        for (int i = 0; i < 10; i++) {
        	data[i][0] = barcodes[i];
            data[i][1] = items[i];
            data[i][2] = prices[i];
        }
        
        return data;
    }
    
    public boolean isFull(int currentIndex) {
    	return currentIndex >= barcodes.length;
    }
    
    public boolean isInventoryEmpty() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && !items[i].isEmpty()) {
                return false;
            }
        }
        return true;
    }
    
    public void saveProductsToFile() {
    	try (PrintWriter pw = new PrintWriter(new FileWriter("inventory.csv"))) {
    		for (int i = 0; i < barcodes.length; i++) {
    			if (items[i] != null && !items[i].isEmpty()) {
    				pw.println(barcodes[i] + "," + items[i] + "," + prices[i]);
    			}
    		}
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
       public void loadProductsFromFile() {
    		try (BufferedReader br = new BufferedReader(new FileReader("inventory.csv"))) {
    			String line;
                int index = 0;
                while ((line = br.readLine()) != null && index < 10) {
                	String[] parts = line.split(",");
                    barcodes[index] = Integer.parseInt(parts[0]);
                    items[index] = parts[1];
                    prices[index] = Float.parseFloat(parts[2]);
                    index++;
                }
    		} catch (IOException e) {
    			
    		}
       }
       
       public void clearInventory() {
    	   for (int i = 0; i < barcodes.length; i++) {
    		   barcodes[i] = 0;
    	       items[i] = "";
    	       prices[i] = 0.0f;
    	   }
    	   
    	   saveProductsToFile();
       }
       
       private void clearSlot(int i) {
    	   barcodes[i] = 0;
           items[i] = "";
           prices[i] = 0.0f;
       }
}
