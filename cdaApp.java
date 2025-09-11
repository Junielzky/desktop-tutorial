package cdaApp;
import java.util.*;
public class cdaApp {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// AMT/1000 = integer
		// 10 variables = 1000, 500, 200, 100, 50, 20, 10, 1 
		Scanner in = new Scanner(System.in);
		 String item1, item2, item3;
		 float price1, price2, price3;
		 float qty1, qty2, qty3;
		 float subtotal1, subtotal2;
		 float grandtotal1, subtotal3;
		 float change1;
		System.out.print("Name of Item: ");
		item1 = in.nextLine();
		System.out.print("Price: ");
		price1 = in.nextFloat();
		if (price1 <0) {
		System.out.println("ERROR!");
		System.exit(0);
		}
		System.out.print("Quantity: ");
		qty1 = in.nextFloat();
		if (qty1 <0) {
		System.out.println("ERROR!");
		System.exit(0);
		}
		subtotal1 = price1*qty1;
		System.out.println("Subtotal: ₱" + subtotal1);
		System.out.println();
		
		System.out.print("Name of Item: ");
		item2 = in.next();
		System.out.print("Price: ");
		price2 = in.nextFloat();
		if (price2 <0) {
		System.out.println("ERROR!");
		System.exit(0);
		}
		System.out.print("Quantity: ");
		qty2 = in.nextFloat();
		
		if (qty2 <0) {
		System.out.println("ERROR!");
		System.exit(0);
		}
		
		subtotal2 = price2*qty2+subtotal1;
		System.out.println("Subtotal: ₱" + subtotal2);
		System.out.println();
		
		System.out.print("Name of Item: ");
		item3 = in.next();
		System.out.print("Price: ");
		price3 = in.nextFloat();
		if (price3 <0) {
		System.out.println("ERROR!");
		System.exit(0);
		}
		
		System.out.print("Quantity: ");
		qty3 = in.nextFloat();
		if (qty3 <0) {
		System.out.println("ERROR!");
		System.exit(0);
		}
		subtotal3 = price3*qty3+subtotal2;
		System.out.println("Subtotal: ₱" + subtotal3);
		System.out.println();
		
		grandtotal1 = subtotal3;
		System.out.println("Grandtotal: ₱" + grandtotal1);
		
		int amount;
		System.out.print ("Enter Amount: ");
		amount = in.nextInt();
		change1 = amount-grandtotal1;
		if (change1 <0) {
		    System.out.println("ERROR!");
			System.exit(0);	
		}
		//Cash Denomination
		System.out.println ("Change: ₱" + change1);
		int change2 = 0, thousand1 = 0, balance1 = 0, fiveH1 =0, oneH1 =0, fifty1 =0, twenty1 =0, ten1=0, five1=0, one1 = 0;
		change2 = (int) change1;
		thousand1 = change2 / 1000;
		balance1 = change2 % 1000;
		fiveH1 = balance1 / 500;
		balance1 %= 500;
		oneH1= balance1 / 100;
		balance1 %= 100;
		fifty1 = balance1 / 50;
		balance1 = balance1%= 50;
		twenty1 = balance1 / 20;
		balance1 = balance1%= 20;
		ten1 = balance1 / 10;
		balance1 = balance1%= 10;
		five1 = balance1 / 5;
		balance1 = balance1%= 5;
		one1 = balance1 / 1;
		balance1%= 1;
	System.out.println ("Cash\t\tNo. of Pcs");
	if (thousand1 != 0) {
    System.out.println ("1000\t\t"+thousand1);}
	if (fiveH1 != 0) {
	System.out.println ("500\t\t"+fiveH1); }
    if (oneH1 != 0) {
    System.out.println ("100\t\t"+oneH1); }
	if (fifty1 != 0) {
	System.out.println ("50\t\t"+fifty1); }
	if (twenty1 != 0) {
	System.out.println ("20\t\t"+twenty1); }
	if (ten1 != 0) {
	System.out.println ("10\t\t"+ten1); }
	if (five1 != 0) {
	System.out.println ("5\t\t"+five1); }
	if (one1 != 0) {
	System.out.println ("1\t\t"+one1); }
		
		
		
		
			
	
}
}
        