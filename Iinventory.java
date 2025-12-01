import java.awt.EventQueue;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.miginfocom.swing.MigLayout;

public class Iinventory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtItemName;
	private JTextField txtPrice;
	private JTable table;
	
	private dataCloud dataCloud = new dataCloud();
	private int currentIndex = 0;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Iinventory frame = new Iinventory();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private DecimalFormat pesoFormat = new DecimalFormat("₱#,##0.00");
	private  int row;
	
	/**
	 * Create the frame.
	 */
	public Iinventory() {
		setTitle("TrioInventory");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 572, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ITEM INVENTORY");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 454, 14);
		contentPane.add(lblNewLabel);
		
		dataCloud.loadProductsFromFile();
		Object[][] data = dataCloud.getAllProducts();
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 35, 236, 88);
		contentPane.add(panel);
		panel.setLayout(new MigLayout("", "[56px][5px][35px][][5px][27px][5px][24px][5px][67px]", "[19px][][][19px]"));
		
		JLabel lblNewLabel_1 = new JLabel("BARCODE");
		panel.add(lblNewLabel_1, "cell 0 0,alignx left,aligny center");
		
		txtBarcode = new JTextField();
		panel.add(txtBarcode, "cell 3 0 7 1,alignx left,aligny top");
		txtBarcode.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ITEM NAME");
		panel.add(lblNewLabel_2, "cell 0 1,alignx left,aligny center");
		
		txtItemName = new JTextField();
		panel.add(txtItemName, "cell 3 1 7 1,alignx left,aligny top");
		txtItemName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PRICE");
		panel.add(lblNewLabel_3, "cell 0 2,alignx left,aligny center");
		
		txtPrice = new JTextField();
		panel.add(txtPrice, "cell 3 2 7 1,alignx left,aligny top");
		txtPrice.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(256, 36, 290, 214);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		model = new DefaultTableModel(
				new Object[] {"BARCODE", "ITEM", "PRICE"},0
		);
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 290, 214);
		panel_1.add(scrollPane);
		
		loadProducts();
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setBounds(10, 147, 110, 23);
		contentPane.add(btnSave);
		
		JButton btnEditItem = new JButton("EDIT");
		btnEditItem.setBounds(136, 147, 110, 23);
		contentPane.add(btnEditItem);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(10, 181, 236, 23);
		contentPane.add(btnDelete);
		
		JButton btnClearInventory = new JButton("CLEAR INVENTORY");
		btnClearInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearInventory();
			}
		});
		btnClearInventory.setBounds(10, 215, 236, 23);
		contentPane.add(btnClearInventory);
		
		txtPrice.addActionListener(e -> addProduct());
		btnSave.addActionListener(e -> saveToModule());
		btnDelete.addActionListener(e -> deleteProduct());
		btnClearInventory.addActionListener(e -> clearInventory());
		
	}
	private void loadProducts() {
		model.setRowCount(0);
		dataCloud dataCloud = new dataCloud();
		dataCloud.loadProductsFromFile();
        for (Object[] row : dataCloud.getAllProducts()) {
        	if (row[1] != null && !row[1].toString().isEmpty()) {
                row[2] = pesoFormat.format(Float.parseFloat(row[2].toString()));
                model.addRow(row);
        	}
        }
	}
	
	private void addProduct() {
		if (model.getRowCount() >= 10) {
			 JOptionPane.showMessageDialog(this, "The item inventory is full!");
	         return;
		}
		
		if (txtBarcode.getText().trim().isEmpty() || 
		        txtItemName.getText().trim().isEmpty() || 
		        txtPrice.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please fill in all fields.");
	        return;
		}
		
		try {
			int barcode = Integer.parseInt(txtBarcode.getText().trim());
            String itemName = txtItemName.getText().trim();
            float price = Float.parseFloat(txtPrice.getText().trim());

            if (itemName.isEmpty()) throw new Exception();

            model.addRow(new Object[]{barcode, itemName, pesoFormat.format(price)});
			dataCloud.Product(model.getRowCount() - 1, barcode, itemName, price);

            clearFields();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Invalid input.");
		}
	}
	
	private void saveToModule() {
		int rowCount = model.getRowCount();
	    if (rowCount == 0) {
	    	JOptionPane.showMessageDialog(this, "No products to save.");
	        return;
	    }
	    
	    if (rowCount > 10) {
	    	JOptionPane.showMessageDialog(this, "Only 10 products can be saved.");
	        return;
	    }
	    
	    try {
	    	for (int i = 0; i < rowCount; i++) {
	    		int barcode = Integer.parseInt(model.getValueAt(i, 0).toString());
	            String itemName = model.getValueAt(i, 1).toString();
	            float price = parsePeso(model.getValueAt(i, 2).toString());
				dataCloud.Product(i, barcode, itemName, price);
	    	}
	    	
			dataCloud.saveProductsToFile();
	    	
	        JOptionPane.showMessageDialog(this, "All products saved!");
	        dispose();
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(this, "Please check your entries.");
	    }
	}
	
	private void editProduct() {
	    int selectedRow = table.getSelectedRow();
	    if (selectedRow == -1) {
	        JOptionPane.showMessageDialog(this, "Please select a row to edit.");
	        return;
	    }

	    try {
	    	int barcode = Integer.parseInt(txtBarcode.getText().trim());
            String itemName = txtItemName.getText().trim();
            float price = Float.parseFloat(txtPrice.getText().trim());

            model.setValueAt(barcode, selectedRow, 0);
            model.setValueAt(itemName, selectedRow, 1);
            model.setValueAt(pesoFormat.format(price), selectedRow, 2);

			dataCloud.Product(selectedRow, barcode, itemName, price);
            JOptionPane.showMessageDialog(this, "Product updated successfully!");
	    } catch (Exception ex) {
	    	JOptionPane.showMessageDialog(this, "Invalid input.");
	    }
	}
	
	private void deleteProduct() {
		int row = table.getSelectedRow();
        if (row == -1) {
        	JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }
        
        model.removeRow(row);
		dataCloud.Product(row, 0, "", 0.0f);
        dataCloud.saveProductsToFile();
        JOptionPane.showMessageDialog(this, "Product deleted successfully!");
	}
	
	private void clearInventory() {
		int confirm = JOptionPane.showConfirmDialog(this, "Delete all products?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
			dataCloud.clearInventory();
            model.setRowCount(0);
            JOptionPane.showMessageDialog(this, "Inventory cleared.");
        }
	}
	
	private void clearFields() {
		txtBarcode.setText("");
        txtItemName.setText("");
        txtPrice.setText("");
        txtBarcode.requestFocus();
	}
	
	private float parsePeso(String val) {
		return Float.parseFloat(val.replace("₱", "").replace(",", ""));
	}
}