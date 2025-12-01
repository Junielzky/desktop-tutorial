import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollBar;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class sYstemInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtItemName;
	private JTextField txtPrice;
	private JTextField txtQuantity;
	private JTextField txtAmountTendered;
	private JTextField txtChange;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dataCloud dataCloud = new dataCloud();
					dataCloud.loadProductsFromFile();
					
					if (dataCloud.isInventoryEmpty()) {
						Iinventory regPanel = new Iinventory();
	                    regPanel.setVisible(true);
					} else {
						sYstemInterface frame = new sYstemInterface();
						frame.setVisible(true);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	float grandTotal = 0.00f;
	private JTextField txtGrandTotal;
	float amountTendered = 0.00f;
	float change = 0.00f;
	private JTable table;
	private DefaultTableModel model;
	private DecimalFormat pesoFormat = new DecimalFormat("₱#,##0.00");

	/**
	 * Create the frame.
	 */
	public sYstemInterface() {
		setTitle("Point of Sale");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 131, 450, 227);
		contentPane.add(panel);
		panel.setLayout(null);
		
		// Table Setup
		model = new DefaultTableModel(
				new Object[] {"Barcode", "Item Name", "Price", "Quantity", "Subtotal"}, 0
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3;
			}
		};
		table = new JTable(model);
		
		model.addTableModelListener(e -> {
			if (e.getColumn() == 3) updateSubtotal(e.getFirstRow());
		});
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 430, 205);
		panel.add(scrollPane);
		
		// Input Fields
		txtBarcode = new JTextField();
		txtItemName = new JTextField();
		txtPrice = new JTextField();
		txtQuantity = new JTextField();
		JTextField subtotal = new JTextField();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(470, 131, 204, 185);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(4, 2, 0, 5));
		
		JLabel lblNewLabel = new JLabel("BARCODE:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel);
		
		txtBarcode = new JTextField();
		txtBarcode.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						int barcode = Integer.parseInt(txtBarcode.getText().trim());
		                dataCloud dataCloud = new dataCloud();
		                dataCloud.searchItem(barcode);
		                
		                if (dataCloud.getItem() == null || dataCloud.getItem().isEmpty()) {
		                	JOptionPane.showMessageDialog(null, "Barcode is not listed yet.");
		                    clearItemFields();
		                    txtBarcode.requestFocus();
		                } else {
		                	txtItemName.setText(dataCloud.getItem());
		                    txtPrice.setText(pesoFormat.format(dataCloud.getPrice())); // formatted
		                    txtQuantity.requestFocus();
		                }
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid barcode input.");
		                clearItemFields();
		                txtBarcode.requestFocus();
					}
				}
			}
		});
		panel_1.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Item Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_1);
		
		txtItemName = new JTextField();
		txtItemName.setEditable(false);
		panel_1.add(txtItemName);
		txtItemName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Price:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_2);
		
		txtPrice = new JTextField();
		txtPrice.setEditable(false);
		panel_1.add(txtPrice);
		txtPrice.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_1.add(lblNewLabel_3);
		
		txtQuantity = new JTextField();
		txtQuantity.addKeyListener(new KeyAdapter() {
			private dataCloud dataCloud;

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						int qty = Integer.parseInt(txtQuantity.getText());
						if (qty <= 0) {
							JOptionPane.showMessageDialog(null, "Quantity must be greater than zero.");
							txtQuantity.setText("");
							txtQuantity.requestFocus();
							return;
						}
						
						float price = parsePeso(txtPrice.getText());
						float subtotal = price * qty;
						String barcode = txtBarcode.getText();
						String itemName = txtItemName.getText();
						boolean merged = false;
						
						for (int i = 0; i < model.getRowCount(); i++) {
							if (model.getValueAt(i, 0).equals(barcode) && model.getValueAt(i, 1).equals(itemName)) {
								int existingQty = Integer.parseInt(model.getValueAt(i, 3).toString());
		                        int newQty = existingQty + qty;
		                        model.setValueAt(newQty, i, 3);
		                        updateSubtotal(i);  
		                        merged = true;
		                        break;
							}
						}
						
						if (!merged) {
							model.addRow(new Object[]{
									barcode, 
									itemName, 
									pesoFormat.format(price), 
									qty, 
									pesoFormat.format(subtotal)
							});
						}
						
						updateGrandTotal();
		                clearItemFields();
			            txtBarcode.requestFocus();
					} 
					
					catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(null, "Invalid quantity input.");
						txtQuantity.setText("");
						txtQuantity.requestFocus();
						
					}
				}	
			}
		});
		panel_1.add(txtQuantity);
		txtQuantity.setColumns(10);

		
		JButton btnClear = new JButton("Clear Transaction");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTransaction();
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClear.setBackground(Color.BLACK);
		btnClear.setForeground(new Color(255, 255, 255));
		btnClear.setBounds(470, 355, 204, 23);
		contentPane.add(btnClear);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
		        if (selectedRow == -1) {
		        	JOptionPane.showMessageDialog(null, "Please select a row to delete.");
		            return;
		        }
		   
		        model.removeRow(selectedRow);
		        
		        updateGrandTotal();

		        JOptionPane.showMessageDialog(null, "Row deleted successfully!");
			}
		});
		btnDelete.setBackground(new Color(0, 0, 0));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnDelete.setBounds(470, 327, 204, 23);
		contentPane.add(btnDelete);
		
		JButton btnProductInventory = new JButton("ITEM INVENTORY");
		btnProductInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Iinventory Iinventory = new Iinventory();
				Iinventory.setVisible(true);
			}
		});
		btnProductInventory.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnProductInventory.setBackground(new Color(0, 0, 0));
		btnProductInventory.setForeground(new Color(255, 255, 255));
		btnProductInventory.setBounds(470, 388, 204, 23);
		contentPane.add(btnProductInventory);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 369, 450, 76);
		contentPane.add(panel_3);
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(192, 192, 192), new Color(192, 192, 192), new Color(0, 0, 0)));
		panel_3.setLayout(new GridLayout(2, 2, 0, 5));
		
		JLabel lblNewLabel_5 = new JLabel("Cash Tendered:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(lblNewLabel_5);
		
		txtAmountTendered = new JTextField();
		txtAmountTendered.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					float amountTendered = Float.parseFloat(txtAmountTendered.getText().trim());
			        
					updateGrandTotal();
			        float effectiveGrandTotal = grandTotal;

			        if (amountTendered < effectiveGrandTotal) {
			        	JOptionPane.showMessageDialog(null, "Insufficient amount!");
			            txtAmountTendered.setText("");
			            txtChange.setText("");
			            txtAmountTendered.requestFocus();
			        } else {
			        	float change = amountTendered - effectiveGrandTotal;
			            txtChange.setText(formatPeso(change)); // peso formatted
			            JOptionPane.showMessageDialog(null, changeSystem.getBreakdown(change));
			        }
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Invalid amount.");
			        txtAmountTendered.setText("");
			        txtAmountTendered.requestFocus();
				}
			}
		});
		panel_3.add(txtAmountTendered);
		txtAmountTendered.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Change:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		panel_3.add(lblNewLabel_6);
		
		txtChange = new JTextField();
		txtChange.setEditable(false);
		panel_3.add(txtChange);
		txtChange.setColumns(10);
		
		txtGrandTotal = new JTextField("0.00");
		txtGrandTotal.setBounds(10, 11, 664, 109);
		contentPane.add(txtGrandTotal);
		txtGrandTotal.setFont(new Font("Tahoma", Font.BOLD, 50));
		txtGrandTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		txtGrandTotal.setEditable(false);
		txtGrandTotal.setColumns(10);

	}
	
	private float computeGrandTotalFromModel() {
		float total = 0.00f;
		for (int i = 0; i < model.getRowCount(); i++) {
			Object val = model.getValueAt(i, 4);
            if (val != null && !val.toString().isEmpty()) {
            	total += parsePeso(val.toString());
            }
		}
		
		return total;
	}
	
	private float parsePeso(String val) {
		return Float.parseFloat(val.replace("₱", "").replace(",", ""));
	}
	
	private String formatPeso(float val) {
		return pesoFormat.format(val);
	}
	
	private void updateGrandTotal() {
		grandTotal = computeGrandTotalFromModel();
	    txtGrandTotal.setText(formatPeso(grandTotal));
	}
	
	private void updateSubtotal(int row) {
		try {
			int qty = Integer.parseInt(model.getValueAt(row, 3).toString());
	        float price = parsePeso(model.getValueAt(row, 2).toString());
	        model.setValueAt(formatPeso(price * qty), row, 4);
	        updateGrandTotal();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Invalid quantity input.");
	        model.setValueAt(0, row, 3);
	        model.setValueAt(formatPeso(0.0f), row, 4);
	        updateGrandTotal();
		}
	}
	
	private void clearItemFields() {
		txtBarcode.setText("");
	    txtItemName.setText("");
	    txtPrice.setText("");
	    txtQuantity.setText("");
	}
	
	private void clearTransaction() {
		txtAmountTendered.setText("");
	    txtChange.setText("");
	    grandTotal = 0.00f;
	    txtGrandTotal.setText(formatPeso(grandTotal));
	    model.setRowCount(0);
	    txtBarcode.requestFocus();
	}
}
