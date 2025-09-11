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
		 float subtotal1, subtotal2, subtotal3;
		 float grandtotal1;
		 float change1;
		 System.out.print("Name of Item: ");
		 item1 = in.next();
		System.out.print("Price: ");
		price1 = in.nextFloat();
		if (price1 <=0) {
		 System.out.println("ERROR!");
		System.exit(0);
		}
		System.out.print("Quantity: ");
		qty1 = in.nextFloat();
		if (qty1 <=0) {
		 System.out.println("ERROR!");
		System.exit(0);
		}
		subtotal1 = price1*qty1;
		System.out.println("Subtotal: " + subtotal1);
		 System.out.print("Name of Item: ");
		 item2 = in.next();
		System.out.print("Price: ");
		price2 = in.nextFloat();
		if (price2 <=0) {
		 System.out.println("ERROR!");
		System.exit(0);
		}
		System.out.print("Quantity: ");
		qty2 = in.nextFloat();
		
		if (qty2 <=0) {
		 System.out.println("ERROR!");
		System.exit(0);
		}
		subtotal2 = price2*qty2;
		System.out.println("Subtotal: " + subtotal2);
		 System.out.print("Name of Item: ");
		 item3 = in.next();
		System.out.print("Price: ");
		price3 = in.nextFloat();
		if (price3 <=0) {
		 System.out.println("ERROR!");
		System.exit(0);
		}
		System.out.print("Quantity: ");
		qty3 = in.nextFloat();
		if (qty3 <=0) {
		 System.out.println("ERROR!");
		System.exit(0);
		}
		subtotal3 = price3*qty3;
		System.out.println("Subtotal: " + subtotal3);
		grandtotal1 = subtotal1+subtotal2+subtotal3;
		System.out.println("Grandtotal: " + grandtotal1);
		int amount = 0, thousand, balance, fiveH, oneH, fifty, twenty, ten, five, one;
		System.out.print ("Enter Amount: ");
		amount = in.nextInt();
		thousand = amount / 1000;
		balance = amount % 1000;
		fiveH = balance / 500;
		balance %= 500;
		oneH = balance / 100;
		balance %= 100;
		fifty = balance / 50;
		balance = balance%= 50;
		twenty = balance / 20;
		balance = balance%= 20;
		ten = balance / 10;
		balance = balance%= 10;
		five = balance / 5;
		balance = balance%= 5;
		one = balance / 1;
		balance%= 1;
		System.out.println ("Cash\t\tNo. of Pcs");
		System.out.println ("1000\t\t"+thousand);
		System.out.println ("500\t\t"+fiveH);
		System.out.println ("100\t\t"+oneH);
		System.out.println ("50\t\t"+fifty);
		System.out.println ("20\t\t"+twenty);
		System.out.println ("10\t\t"+ten);
		System.out.println ("5\t\t"+five);
		System.out.println ("1\t\t"+one);
		System.out.println ("Balance: " + balance);
		change1 = amount-grandtotal1;
		System.out.println ("Change: " + change1);
		if (change1 <=0) {
	  System.out.println("ERROR!");
		System.exit(0);
		}
		
		
		 
		 
		 
		 
		
		

	}

}
