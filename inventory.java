package FinalOutPut;

import java.awt.EventQueue;
import java.awt.Font;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class inventory extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel model = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inventory frame = new inventory();
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
	public inventory() {
		setTitle("ProductInventoryList");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		model.addColumn("ITEM");
        model.addColumn("PRICE");
        model.addColumn("QUANTITY");
        model.addColumn("SUBTOTAL");
        JTable table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 527, 210);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 507, 190);
		panel.add(scrollPane);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(10, 233, 527, 47);
		contentPane.add(panel_1_1);
		
		JButton btnSave1 = new JButton("SAVE");
		btnSave1.setBounds(141, 10, 84, 27);
		panel_1_1.add(btnSave1);
		
		JButton btnDelete1 = new JButton("DELETE");
		btnDelete1.setBounds(10, 10, 84, 27);
		panel_1_1.add(btnDelete1);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBounds(281, 11, 84, 24);
		panel_1_1.add(btnEdit);
		
		JButton btnClearInventory = new JButton("CLEAR");
		btnClearInventory.setBounds(411, 11, 91, 24);
		panel_1_1.add(btnClearInventory);
		
		//txtPrice.addactionListener(e -> addProduct());
		btnSave1.addActionListener(e -> savetofile());
		btnDelete1.addActionListener(e -> deleteProduct());
		btnEdit.addActionListener(e -> editProduct());
		btnClearInventory.addActionListener(e -> Product());
		
	}
}

	private void loadProducts() {
		model.setRowCount(0);
		datacloud.loadProductsFromFile();
		for (Object[] row: datacloud.getAllProducts()) {
		if (row[1] != null && !row[1].toString().isEmpty()) {
			row[2] pesoFormat.format(Float.parseFloat(row[2].toString()));
		model.addRow(row);
		
		}
		}
		
}
	private void addProduct() {
		if (model.getRowCount() >= 10) {
			JOptionPane.showMessageDialog(this, "The product inventory is full!");
		}
		return;
		}
		try {

			int barcode = Integer.parseInt(txtBarcode.getText().trim());
			String itemName = txtItemName.getText().trim();
			float price Float.parseFloat(txtPrice.getText().trim());
			
			if (itemName.isEmpty()) throw new Exception();
			model.addRow(new Object[]{barcode, itemName, pesoFormat.format(price)});
			datacloud.registerProduct(model.getRowCount()1, barcode, itemName, price);

			clearFields();

		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Invalid input. Please check fields.");
}
		
		
	private void saveToModule() {

			int rowCount model.getRowCount();
			if (rowCount 0) {
			}
				JOptionPane.showMessageDialog(this, "No products to save.");
				return;

			if (rowCount > 10) {
				JOptionPane.showMessageDialog(this, "Only 10 products can be saved.");	
				return;
			try {
				for (int i=0; i < rowCount; i++) {
					int barcode = Integer.parseInt(model.getValueAt(i, 0).toString());
					String itemName model.getValueAt(i, 1).toString();
					float price parsePeso (model.getValueAt(i, 2).toString());
					datacloud.registerProduct(i, barcode, itemName, price);
				}
				datacloud.saveProductsToFile();
				
				JOptionPane.showMessageDialog(this, "All products saved!");
				dispose();
			
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Invalid data in table. Please check your entries.");
		}
			}	
		
	private void editProduct() {
			int selected Row table.getSelectedRow();
			If (selectedRow == -1) {
				JOptionPane.showMessageDialog(this, "Please select a row to edit.");
				return;
			}
			
			try {
				int barcode = Integer.parseInt(txtBarcode.getText().trim());
				String itemName = txtItemName.getText().trim());
				float price = Float.parseFloat(txtPrice.getText().trim());			}
