package FinalOutPut;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.FlowLayout;

public class bCTrio extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtBarcode;
    private JTextField txtDescription;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JTable table;
    private JTextField txtGrandtotal;
    DefaultTableModel model = new DefaultTableModel();
    private JTextField txtAmountendered;
    
    
    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	bCTrio frame = new bCTrio();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public bCTrio() {
    	setTitle("Point Of Sale: Project");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 882, 501);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(2, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
        panel_1.setBounds(478, 104, 350, 139);
        contentPane.add(panel_1);
        
        model.addColumn("ITEM");
        model.addColumn("PRICE");
        model.addColumn("QUANTITY");
        model.addColumn("SUBTOTAL");
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel_1.setLayout(new MigLayout("", "[192px][1px][192px]", "[39px][2px][37px][2px][37px][2px][39px]"));
        
      
        JLabel lblBarcode = new JLabel("BARCODE:");
        lblBarcode.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_1.add(lblBarcode, "cell 0 0,alignx left,growy");

        txtBarcode = new JTextField();
        txtBarcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtBarcode.setToolTipText("");
        txtBarcode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode() == KeyEvent.VK_ENTER)
            	{
            		datacloud posdata = new datacloud();
            		posdata.SearchItem(txtBarcode.getText());
            		if (!posdata.Available()) 
                		JOptionPane.showMessageDialog(null, "Item Unavailable");
            		else
            		{
            			txtDescription.setText(posdata.getItem());
            			txtPrice.setText(""+posdata.getPrice());
            			txtQuantity.requestFocus();
            		}
            	}
            }
        });
        txtBarcode.setBorder(new LineBorder(Color.BLACK));
        panel_1.add(txtBarcode, "cell 2 0 1 2,grow");
        txtBarcode.setColumns(10);

        JLabel lblItem = new JLabel("DESCRIPTION:");
        lblItem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_1.add(lblItem, "cell 0 1 1 2,grow");

        txtDescription = new JTextField();
        txtDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDescription.setEditable(false);
        txtDescription.setColumns(10);
        txtDescription.setBorder(new LineBorder(Color.BLACK));
        panel_1.add(txtDescription, "cell 2 2 1 2,grow");

        JLabel lblPrice = new JLabel("PRICE:");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_1.add(lblPrice, "cell 0 3 3 2,alignx left,growy");

        txtPrice = new JTextField();
        txtPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtPrice.setEditable(false);
        txtPrice.setColumns(10);
        txtPrice.setBorder(new LineBorder(Color.BLACK));
        panel_1.add(txtPrice, "cell 2 4 1 2,grow");

        JLabel lblQuantity = new JLabel("QUANTITY:");
        lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_1.add(lblQuantity, "cell 0 5 3 2,alignx left,growy");

        txtQuantity = new JTextField();
        txtQuantity.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode() == KeyEvent.VK_ENTER)
        		{
        			try {

	        			int qty = (int) Float.parseFloat(txtQuantity.getText());
	        			float price = Float.parseFloat(txtPrice.getText());
	        		    float subtotal = qty * price;
	        		
	        		    InsertItem(subtotal, qty);
	        		    GenerateGrandTotal();
	    		    	ClearFields();
        			} 
        			catch (NumberFormatException ex) {
        				JOptionPane.showMessageDialog(null, "Invalid input.");
        			}
        			/* (Qty < 0) {
        				
        			}*/
        		}
        	}
        });
        txtQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtQuantity.setColumns(10);
        txtQuantity.setBorder(new LineBorder(Color.BLACK));
        panel_1.add(txtQuantity, "cell 2 6,grow");

        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        panel.setBounds(43, 104, 406, 282);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 406, 282);
        panel.add(scrollPane);
         
        scrollPane.setViewportView(table);
        txtGrandtotal = new JTextField();
        txtGrandtotal.setBackground(Color.WHITE);
        txtGrandtotal.setEditable(false);
        txtGrandtotal.setFont(new Font("Tahoma", Font.BOLD, 21));
        txtGrandtotal.setText("TOTAL: ₱");
        txtGrandtotal.setBounds(43, 24, 406, 63);
        contentPane.add(txtGrandtotal);
        txtGrandtotal.setColumns(10);
        
        JPanel logo = new JPanel();
        logo.setBounds(699, 24, 132, 56);
        contentPane.add(logo);
        logo.setLayout(null);
        
        JLabel lblNewLabel_4 = new JLabel("LOGO");
        lblNewLabel_4.setBounds(10, 10, 123, 36);
        logo.add(lblNewLabel_4);
        
        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    model.removeRow(selectedRow);
                    GenerateGrandTotal();
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete.");
                }
        	}
        });
        btnDelete.setBounds(271, 396, 84, 20);
        contentPane.add(btnDelete);
        
        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    try {
                        int newQty = (int) Float.parseFloat(JOptionPane.showInputDialog("Enter new quantity:"));
                        float price = Float.parseFloat(model.getValueAt(selectedRow, 1).toString());
                        float newSub = newQty * price;
                        model.setValueAt(newQty, selectedRow, 2);
                        model.setValueAt(newSub, selectedRow, 3);
                        GenerateGrandTotal();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid input.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to update.");
                }
        	}
        });
        btnUpdate.setBounds(365, 396, 84, 20);
        contentPane.add(btnUpdate);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(500, 361, 305, 45);
        contentPane.add(panel_2);
        panel_2.setLayout(null);
        
        JLabel lblPayment = new JLabel("Payment:");
        lblPayment.setBounds(10, 6, 69, 32);
        lblPayment.setFont(new Font("Tahoma", Font.PLAIN, 15));
        panel_2.add(lblPayment);
        
        txtAmountendered = new JTextField();
        txtAmountendered.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        		
        	}
        });
        txtAmountendered.setBounds(89, 10, 101, 27);
        panel_2.add(txtAmountendered);
        txtAmountendered.setColumns(10);
        
        JButton btnPay = new JButton("PAY");
        btnPay.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
        	}
        });
        btnPay.setBounds(200, 10, 95, 28);
        btnPay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JOptionPane.showInternalMessageDialog(null,"Pay Now");
        		float AmountEntered = Float.parseFloat(txtAmountendered.getSelectedText());
        		float Grandtotal = 0;
				float Change = AmountEntered - Grandtotal;
        		GenerateGrandTotal();
        		JOptionPane.showInternalMessageDialog(null,"Change"+ Change);
        		
        		
        	}
        });
        panel_2.add(btnPay);
        
        JLabel lblDeveloper = new JLabel("DEVELOPER: Clenn, Juniel ,Kenneth");
        lblDeveloper.setBounds(10, 442, 237, 12);
        contentPane.add(lblDeveloper);
        
        JPanel Utilities = new JPanel();
        Utilities.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
        Utilities.setBounds(479, 248, 349, 168);
        contentPane.add(Utilities);
        Utilities.setLayout(null);
        
        JButton btnRecord = new JButton("RECORD NEW");
        btnRecord.setBackground(new Color(240, 240, 240));
        btnRecord.setBounds(20, 61, 150, 35);
        Utilities.add(btnRecord);
        btnRecord.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
        JButton btnEnter = new JButton("INSERT ITEM");
        btnEnter.setBackground(new Color(255, 255, 255));
        btnEnter.setBounds(20, 10, 307, 29);
        Utilities.add(btnEnter);
        
        JButton btnSave = new JButton("SAVE");
        btnSave.setBackground(new Color(240, 240, 240));
        btnSave.setBounds(180, 61, 147, 35);
        Utilities.add(btnSave);
        btnEnter.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
   }
	public void ClearFields()
	{
		txtBarcode.setText("");
		txtDescription.setText("");
		txtPrice.setText("");
		txtQuantity.setText("");
		txtBarcode.requestFocus();
	}

	public void InsertItem(float subtotal, float presentQty)
	{
		boolean found = false;
		for(int j = 0; j < model.getRowCount(); j++) {			
	    	if (txtDescription.getText().equals(model.getValueAt(j, 0).toString()))
	    	{
		    	JOptionPane.showMessageDialog(null, "Item Exist Already");
		    	int pastQty = (int) Float.parseFloat(model.getValueAt(j, 2).toString());
		    	int newQty = (int) (presentQty + pastQty);
		    	model.setValueAt(newQty, j, 2);
		    		
		    	float price = Float.parseFloat(model.getValueAt(j,1).toString());
		    	float newSub = price * newQty;
		    	model.setValueAt(newSub, j, 3);
		    	found = true;	
	    	}
		}
		if(!found)
		{
			model.addRow(new Object[] { 
					txtDescription.getText(),
					txtPrice.getText(),
					txtQuantity.getText(),
					subtotal
			});
		}
		
	}
	public void GenerateGrandTotal() {
		float total = 0; 
			for(int j = 0; j < model.getRowCount(); j++) {
		    	total += Float.parseFloat(model.getValueAt(j, 3).toString());
			}
		 txtGrandtotal.setText("TOTAL: ₱"+total);
	}
	public void payMent() {
		
	}
}