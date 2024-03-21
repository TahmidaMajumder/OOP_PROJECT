import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;


//Payment(SubClass) is inherited from Menu(Super Class)

public class Payment extends Menu{

	private JFrame frame;
	private JTextField pay;
	private JLabel subtotal;
	private double subtotalValue;
	private double totalValue;
	private double taxValue;
	private JTable table;


	public JFrame getFrame() {
        return frame;
    }
	
	// Constructor overloaded
	public Payment(double subtotalValue, double totalValue, double taxValue) {
	        this.subtotalValue = subtotalValue;
	        this.totalValue = totalValue;
	        this.taxValue = taxValue;
	        initialize();
	  }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(235, 250, 248));
		frame.setBounds(100, 100, 900, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBounds(0, 0, 884, 573);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_PaymentContent = new JPanel();
		panel_PaymentContent.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), null, null));
		panel_PaymentContent.setBackground(new Color(100, 149, 237));
		panel_PaymentContent.setBounds(42, 123, 367, 419);
		panel.add(panel_PaymentContent);
		panel_PaymentContent.setLayout(null);
		
		// SubTotal Label and SubTotal Counting design
		
		JLabel lblSubtotal = new JLabel("Subtotal : ");
		lblSubtotal.setForeground(new Color(255, 255, 255));
		lblSubtotal.setBounds(53, 53, 124, 36);
		panel_PaymentContent.add(lblSubtotal);
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel subtotal = new JLabel("00.00");
		subtotal.setForeground(new Color(255, 255, 255));
		subtotal.setBounds(187, 53, 124, 36);
		panel_PaymentContent.add(subtotal);
		subtotal.setHorizontalAlignment(SwingConstants.LEFT);
		subtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		subtotal.setText(String.format("%.2f", subtotalValue));
		
		// Tax Label and tax Counting design
		
		JLabel lblTax = new JLabel("Tax : ");
		lblTax.setForeground(new Color(255, 255, 255));
		lblTax.setBounds(53, 99, 124, 36);
		panel_PaymentContent.add(lblTax);
		lblTax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		JLabel tax = new JLabel("00.00");
		tax.setForeground(new Color(255, 255, 255));
		tax.setBounds(187, 99, 124, 36);
		panel_PaymentContent.add(tax);
		tax.setHorizontalAlignment(SwingConstants.LEFT);
		tax.setFont(new Font("Tahoma", Font.BOLD, 16));
		tax.setText(String.format("%.2f", taxValue));
		
		// Total Label and total Counting design

		JLabel total = new JLabel("00.00");
		total.setForeground(new Color(255, 255, 255));
		total.setBounds(187, 145, 124, 36);
		panel_PaymentContent.add(total);
		total.setHorizontalAlignment(SwingConstants.LEFT);
		total.setFont(new Font("Tahoma", Font.BOLD, 16));
		total.setText(String.format("%.2f", totalValue));
		
		JLabel lblTotal = new JLabel("Total : ");
		lblTotal.setForeground(new Color(255, 255, 255));
		lblTotal.setBounds(53, 145, 124, 36);
		panel_PaymentContent.add(lblTotal);
		lblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		JLabel lblCash = new JLabel("Cash : ");
		lblCash.setForeground(new Color(255, 255, 255));
		lblCash.setBounds(53, 247, 124, 28);
		panel_PaymentContent.add(lblCash);
		lblCash.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCash.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		pay = new JTextField();
		pay.setForeground(new Color(0, 0, 0));
		pay.setBounds(186, 247, 117, 28);
		panel_PaymentContent.add(pay);
		pay.setFont(new Font("Tahoma", Font.BOLD, 16));
		pay.setColumns(10);


		JLabel balance = new JLabel("00.00");
		balance.setForeground(new Color(255, 255, 255));
		balance.setBounds(187, 286, 124, 28);
		panel_PaymentContent.add(balance);
		balance.setHorizontalAlignment(SwingConstants.LEFT);
		balance.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblBalance = new JLabel("Balance : ");
		lblBalance.setForeground(new Color(255, 255, 255));
		lblBalance.setBounds(53, 286, 124, 28);
		panel_PaymentContent.add(lblBalance);
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalance.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		// 1. Cash input is checked
		// 2. If First condition is ok then balance is calculated if necessary
		// 3. Reset Table, Subtotal, Tax, Total, Cash, Balance
		
		JButton jbtnPay = new JButton("Pay");
		jbtnPay.setForeground(new Color(255, 255, 255));
		jbtnPay.setBackground(new Color(0, 250, 154));
		jbtnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double totalprice = Double.valueOf(total.getText());
					double paid = Double.valueOf(pay.getText());
					
					if (paid < totalprice || pay.getText().isEmpty()) {
					// Dialogue box Purchase is canceled because of insufficient amount 
						JOptionPane.showMessageDialog(frame, "Purchase canceled. Please enter a valid amount.");
						return; // Exit the method without further processing
					}
				double bal = paid - totalprice;
				DecimalFormat df = new DecimalFormat("00.00");
				balance.setText(String.valueOf(df.format(bal)));
            
				//Thank you  message dialogue
				JOptionPane.showMessageDialog(frame, "Thank you for your purchase!");
				
				DefaultTableModel dt = (DefaultTableModel) table.getModel();
				dt.setRowCount(0); // Remove all rows
				subtotal.setText("00.00"); // Reset subtotal amount
				tax.setText("00.00"); // Reset tax amount
				total.setText("00.00"); // Reset total amount
				pay.setText("");// Remove Paid  amount
				balance.setText("00.00");// Reset the "Balance" label
				} catch (NumberFormatException ex) {
					// Dialogue box for invalid input (string)
					JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid number.");
				}
			}
		});
		jbtnPay.setFont(new Font("Tahoma", Font.BOLD, 20));
		jbtnPay.setBounds(108, 357, 149, 34);
		panel_PaymentContent.add(jbtnPay);
		
		JLabel lblNewLabel = new JLabel("Payment Amount :");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(21, 11, 290, 36);
		panel_PaymentContent.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblPaymentMethod = new JLabel("Payment Method :");
		lblPaymentMethod.setForeground(new Color(255, 255, 255));
		lblPaymentMethod.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPaymentMethod.setBounds(21, 192, 290, 36);
		panel_PaymentContent.add(lblPaymentMethod);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(479, 157, 360, 385);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);

		// Set the model for table
		table.setModel(new DefaultTableModel(
		    new Object[][] {},
		    new String[] {"ID", "Name", "Quantity", "Price"}
		));
		
		JLabel lblContentOfThe = new JLabel("Content Of The Order :");
		lblContentOfThe.setForeground(new Color(255, 255, 255));
		lblContentOfThe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblContentOfThe.setBounds(479, 113, 290, 36);
		panel.add(lblContentOfThe);
		
		JLabel lblNewLabel_1 = new JLabel("*****Payment Process*****");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(223, 23, 448, 65);
		panel.add(lblNewLabel_1);

		// Note* : Payment Page Table data are loaded from the txt file which was saved in the menu page
		
		String filePath = "SavedItem.txt";

		DefaultTableModel model = (DefaultTableModel) table.getModel();

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    // Read each line from the file and add it to the table
		    while ((line = reader.readLine()) != null) {
		        // Split the line by whitespace to extract ID, name, quantity, and price
		        String[] parts = line.split("\\s+");
		        // Add a new row to the table model with the extracted data
		        model.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3]});
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
		table.getColumnModel().getColumn(0).setPreferredWidth(44);
		table.getColumnModel().getColumn(1).setPreferredWidth(176);
	}
}
