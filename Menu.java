import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;



public class Menu {

	private JTable table;
	private JLabel q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12;
	private JLabel total;
	private JLabel tax;
	private JLabel subtotal;
	JFileChooser fileChooser;
	
	private Payment payment; // Payment Class Object

	
	public JFrame frmMenu;
	
	public Menu() {
		initialize();
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		
		}
	// **************** getters for Payment Class*****************
	// They are passed into the Payment Method In The Next Button--> Subtotal, Tax, Total
	
	public double getSubtotalValue() {
	    return Double.parseDouble(subtotal.getText());
	}

	public double getTotalValue() {
	    return Double.parseDouble(total.getText());
	}
	
	public double getTaxValue() {
	    return Double.parseDouble(tax.getText());
	}
	
	
	// *******************Functions************************
	
	// 1. Takes 4 Parameter's, 
	// 2. Calculate Total
	// 3. Check If Same Named Exists
	// 4. Add All The Parameter's into Vector
	// 5. Using addRow() Add The Vector v
	
	public void addtable(int ID, String Name, int Qty, Double Amount) {
		DefaultTableModel dt = (DefaultTableModel)table.getModel();
		
		DecimalFormat df = new DecimalFormat("00.00");
		double totalPrice = Amount * Double.valueOf(Qty);
		String totalAmount = df.format(totalPrice);
		
		for(int row = 0; row < table.getRowCount(); row++) {
			
			if (Name.equals(table.getValueAt(row, 1))) {
				dt.removeRow(table.convertRowIndexToModel(row));
			}
		}
		
		Vector v = new Vector();
		
		v.add(ID);
		v.add(Name);
		v.add(Qty);
		v.add(totalAmount);
		
		dt.addRow(v);
	}
	
	
	// 1. Intialize Variables
	// 2. With for loop go through the 3rd index which is Amount Column For Each Row and retrive using parseDouble()
	// 3. Total is counted with total amount plus tax(2.5%)
	// 4. Tax is counted on the total amount
	// 5. Subtotal is product total without tax amount
	
	
	// Note: Intially amounts are taken as double--> converted into decimal --> toString
	public void cal() {
	    int numOfRow = table.getRowCount();
	    double totalamount = 0.0;
	    double Tax = 0.0;
	    double Subtotal = 0.0;

	    for (int i = 0; i < numOfRow; i++) {
	        double value = Double.parseDouble(table.getValueAt(i, 3).toString());
	        totalamount += value;
	    }
	    
	    DecimalFormat df = new DecimalFormat("0.00");
	    String formattedTotal = df.format(totalamount + ((totalamount * 2.5)/100));
	    total.setText(formattedTotal);
	    
	    String formattedTax = df.format((totalamount * 2.5)/100);
	    tax.setText(formattedTax);
	    
	    String formattedSubtotal = df.format(totalamount);
	    subtotal.setText(formattedSubtotal);
	}
	
	// 1. Specify the file path
	// 2. Create a File object with the specified file path
	// 3. Create a FileWriter to write to the file (this will overwrite existing content)
	// 4. Write table data to the file
	// 5. Close the FileWriter
	
	public void saveItemsToFile() {
        String filePath = "SavedItems.txt";

        try {
            
            String filePath1 = "SavedItem.txt";  // 1
            File savedFile = new File(filePath1);  // 2
            FileWriter writer = new FileWriter(savedFile, false); // false to overwrite existing content & 3
            
			// 4
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    writer.write(table.getValueAt(i, j).toString() + "\t");
                }
                writer.write("\n");
            }
            
            writer.close(); // 5
        } catch (IOException ex) {
            // Show a message dialog if an error occurs during file writing
            JOptionPane.showMessageDialog(frmMenu, "Error saving data to file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	
	//  *******Frame Design and All It's Content***********

	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.getContentPane().setBackground(new Color(100, 149, 237));
		frmMenu.setBounds(100, 100, 900, 612);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(100, 149, 237));
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255), new Color(255, 255, 255)));
		panel.setBounds(0, 112, 523, 428);
		frmMenu.getContentPane().add(panel);
		panel.setLayout(null);
		
		// Buttons ActionListener, design, and quantity Label for Menu Panel
		
		// 1
		JButton jbtnBeefBurger = new JButton("");
		jbtnBeefBurger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q1.getText());
				++i;
				q1.setText(String.valueOf(i));
				
				addtable(1, "Beef_Burger", i, 18.00);
				cal();
			}
		});
		jbtnBeefBurger.setIcon(new ImageIcon(getClass().getResource("/pic/Beef Burger.jpg")));
		jbtnBeefBurger.setBounds(10, 11, 121, 101);
		panel.add(jbtnBeefBurger);
		
		q1 = new JLabel("0");
		q1.setForeground(new Color(255, 255, 255));
		q1.setFont(new Font("Tahoma", Font.BOLD, 12));
		q1.setHorizontalAlignment(SwingConstants.CENTER);
		q1.setBounds(10, 115, 121, 25);
		panel.add(q1);
		
		// 2
		
		JButton jbtnChickenPastries = new JButton("");
		jbtnChickenPastries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q2.getText());
		        ++i;
		        q2.setText(String.valueOf(i));
		        
		        addtable(2, "Chicken_Pastries", i, 17.00); 
		        cal();
			}
		});
		jbtnChickenPastries.setIcon(new ImageIcon(getClass().getResource("/pic/Chicken Pastries.jpg")));
		jbtnChickenPastries.setBounds(141, 11, 121, 101);
		panel.add(jbtnChickenPastries);
		
		q2 = new JLabel("0");
		q2.setForeground(new Color(255, 255, 255));
		q2.setHorizontalAlignment(SwingConstants.CENTER);
		q2.setFont(new Font("Tahoma", Font.BOLD, 12));
		q2.setBounds(141, 115, 121, 25);
		panel.add(q2);
		
		// 3
		
		JButton jbtnCroissant = new JButton("");
		jbtnCroissant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q3.getText());
		        ++i;
		        q3.setText(String.valueOf(i));
		        
		        addtable(3, "Croissant_Sandwich", i, 18.00); 
		        cal();
			}
		});
		jbtnCroissant.setIcon(new ImageIcon(getClass().getResource("/pic/Croissant Sandwich.jpg")));
		jbtnCroissant.setBounds(272, 11, 121, 101);
		panel.add(jbtnCroissant);
		
		q3 = new JLabel("0");
		q3.setForeground(new Color(255, 255, 255));
		q3.setHorizontalAlignment(SwingConstants.CENTER);
		q3.setFont(new Font("Tahoma", Font.BOLD, 12));
		q3.setBounds(272, 115, 121, 25);
		panel.add(q3);
		
		// 4
		
		JButton jbtnFries = new JButton("");
		jbtnFries.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q4.getText());
		        ++i;
		        q4.setText(String.valueOf(i));
		        
		        addtable(4, "Fries", i, 8.00); 
		        cal();
			}
		});
		jbtnFries.setIcon(new ImageIcon(getClass().getResource("/pic/Fries.jpg")));
		jbtnFries.setBounds(402, 11, 121, 101);
		panel.add(jbtnFries);
		
		q4 = new JLabel("0");
		q4.setForeground(new Color(255, 255, 255));
		q4.setHorizontalAlignment(SwingConstants.CENTER);
		q4.setFont(new Font("Tahoma", Font.BOLD, 12));
		q4.setBounds(402, 115, 121, 25);
		panel.add(q4);
		
		// 5
		
		JButton jbtnSausageWrap = new JButton("");
		jbtnSausageWrap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q5.getText());
		        ++i;
		        q5.setText(String.valueOf(i));
		        
		        addtable(5, "Sausage_Wrap", i, 13.00); 
		        cal();
			}
		});
		jbtnSausageWrap.setIcon(new ImageIcon(getClass().getResource("/pic/Sausage Wrap.jpg")));
		jbtnSausageWrap.setBounds(10, 151, 121, 101);
		panel.add(jbtnSausageWrap);
		
		q5 = new JLabel("0");
		q5.setForeground(new Color(255, 255, 255));
		q5.setHorizontalAlignment(SwingConstants.CENTER);
		q5.setFont(new Font("Tahoma", Font.BOLD, 12));
		q5.setBounds(10, 255, 121, 25);
		panel.add(q5);
		
		// 6
		
		JButton jbtnSandwich = new JButton("");
		jbtnSandwich.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q6.getText());
		        ++i;
		        q6.setText(String.valueOf(i));
		        
		        addtable(6, "Sandwich", i, 14.00); 
		        cal();
			}
		});
		jbtnSandwich.setIcon(new ImageIcon(getClass().getResource("/pic/Sandwich.jpg")));
		jbtnSandwich.setBounds(141, 151, 121, 101);
		panel.add(jbtnSandwich);
		
		q6 = new JLabel("0");
		q6.setForeground(new Color(255, 255, 255));
		q6.setHorizontalAlignment(SwingConstants.CENTER);
		q6.setFont(new Font("Tahoma", Font.BOLD, 12));
		q6.setBounds(141, 255, 121, 25);
		panel.add(q6);
		
		// 7
		
		JButton jbtnSamosas = new JButton("");
		jbtnSamosas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q7.getText());
		        ++i;
		        q7.setText(String.valueOf(i));
		        
		        addtable(7, "Samosas", i, 5.00); 
		        cal();
			}
		});
		jbtnSamosas.setIcon(new ImageIcon(getClass().getResource("/pic/Samosas.jpg")));
		jbtnSamosas.setBounds(272, 151, 121, 101);
		panel.add(jbtnSamosas);
		
		q7 = new JLabel("0");
		q7.setForeground(new Color(255, 255, 255));
		q7.setHorizontalAlignment(SwingConstants.CENTER);
		q7.setFont(new Font("Tahoma", Font.BOLD, 12));
		q7.setBounds(272, 255, 121, 25);
		panel.add(q7);
		
		// 8
		
		JButton jbtnSalad = new JButton("");
		jbtnSalad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q8.getText());
		        ++i;
		        q8.setText(String.valueOf(i));
		        
		        addtable(8, "Salad", i, 11.00); 
		        cal();
			}
		});
		jbtnSalad.setIcon(new ImageIcon(getClass().getResource("/pic/Salad.jpg")));
		jbtnSalad.setBounds(402, 151, 121, 101);
		panel.add(jbtnSalad);
		
		q8 = new JLabel("0");
		q8.setForeground(new Color(255, 255, 255));
		q8.setHorizontalAlignment(SwingConstants.CENTER);
		q8.setFont(new Font("Tahoma", Font.BOLD, 12));
		q8.setBounds(402, 255, 121, 25);
		panel.add(q8);
		
		// 9
		
		JButton jbtnHotdog = new JButton("");
		jbtnHotdog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q9.getText());
			    ++i;
			    q9.setText(String.valueOf(i));
			        
			    addtable(9, "Hotdog", i, 10.00); 
			    cal();
			}
		});
		jbtnHotdog.setIcon(new ImageIcon(getClass().getResource("/pic/Hotdog.jpg")));
		jbtnHotdog.setBounds(10, 288, 121, 101);
		panel.add(jbtnHotdog);
		
		q9 = new JLabel("0");
		q9.setForeground(new Color(255, 255, 255));
		q9.setHorizontalAlignment(SwingConstants.CENTER);
		q9.setFont(new Font("Tahoma", Font.BOLD, 12));
		q9.setBounds(10, 392, 121, 25);
		panel.add(q9);
		
		// 10
		
		JButton jbtnOnionring = new JButton("");
		jbtnOnionring.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q10.getText());
			    ++i;
			    q10.setText(String.valueOf(i));
			        
			    addtable(10, "Onion_Ring", i, 7.00); 
			    cal();
			}
		});
		jbtnOnionring.setIcon(new ImageIcon(getClass().getResource("/pic/Onionring.jpg")));
		jbtnOnionring.setBounds(141, 288, 121, 101);
		panel.add(jbtnOnionring);
		
		q10 = new JLabel("0");
		q10.setForeground(new Color(255, 255, 255));
		q10.setHorizontalAlignment(SwingConstants.CENTER);
		q10.setFont(new Font("Tahoma", Font.BOLD, 12));
		q10.setBounds(141, 392, 121, 25);
		panel.add(q10);
		
		// 11
		
		JButton jbtnFriedChicken = new JButton("");
		jbtnFriedChicken.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q11.getText());
			    ++i;
			    q11.setText(String.valueOf(i));
			        
			    addtable(11, "Fried_Chicken", i, 15.00); 
			    cal();
			}
		});
		jbtnFriedChicken.setIcon(new ImageIcon(getClass().getResource("/pic/Fried Chicken.jpg")));
		jbtnFriedChicken.setBounds(272, 288, 121, 101);
		panel.add(jbtnFriedChicken);
		
		q11 = new JLabel("0");
		q11.setForeground(new Color(255, 255, 255));
		q11.setHorizontalAlignment(SwingConstants.CENTER);
		q11.setFont(new Font("Tahoma", Font.BOLD, 12));
		q11.setBounds(272, 392, 121, 25);
		panel.add(q11);
		
		// 12
		
		JButton jbtnChips = new JButton("");
		jbtnChips.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = Integer.valueOf(q12.getText());
			    ++i;
			    q12.setText(String.valueOf(i));
			        
			    addtable(12, "Chips", i, 7.00); 
			    cal();
			}
		});
		jbtnChips.setIcon(new ImageIcon(getClass().getResource("/pic/Chips.jpg")));
		jbtnChips.setBounds(402, 288, 121, 101);
		panel.add(jbtnChips);
		
		q12 = new JLabel("0");
		q12.setForeground(new Color(255, 255, 255));
		q12.setHorizontalAlignment(SwingConstants.CENTER);
		q12.setFont(new Font("Tahoma", Font.BOLD, 12));
		q12.setBounds(402, 392, 121, 25);
		panel.add(q12);
		
		// Table For item's are inside the Scoll Panel

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(543, 112, 331, 318);
		frmMenu.getContentPane().add(scrollPane);
		
		
		// Table 
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Item Name", "Qty", "Amount"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(46);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(58);
		table.getColumnModel().getColumn(3).setPreferredWidth(74);
		scrollPane.setViewportView(table);
		
		// Total Label and total Counting design
		
		JLabel jlblTotal = new JLabel("Total : ");
		jlblTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		jlblTotal.setBounds(543, 534, 105, 28);
		frmMenu.getContentPane().add(jlblTotal);
		
		JLabel total = new JLabel("00.00");
		total.setHorizontalAlignment(SwingConstants.LEFT);
		total.setFont(new Font("Tahoma", Font.BOLD, 16));
		total.setBounds(658, 534, 105, 28);
		frmMenu.getContentPane().add(total);
		this.total = total;
		
		// Tax Label and tax Counting design
		
		JLabel lblTax = new JLabel("Tax : ");
		lblTax.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTax.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTax.setBounds(543, 501, 105, 28);
		frmMenu.getContentPane().add(lblTax);
		
		JLabel tax = new JLabel("00.00");
		tax.setHorizontalAlignment(SwingConstants.LEFT);
		tax.setFont(new Font("Tahoma", Font.BOLD, 16));
		tax.setBounds(658, 501, 105, 28);
		frmMenu.getContentPane().add(tax);
		this.tax = tax;
		
		// Subtotal Label and tax Counting design
		
		JLabel lblSubtotal = new JLabel("Subtotal : ");
		lblSubtotal.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSubtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubtotal.setBounds(543, 466, 105, 31);
		frmMenu.getContentPane().add(lblSubtotal);
		
		JLabel subtotal = new JLabel("00.00");
		subtotal.setHorizontalAlignment(SwingConstants.LEFT);
		subtotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		subtotal.setBounds(658, 466, 105, 34);
		frmMenu.getContentPane().add(subtotal);
		this.subtotal = subtotal;
		
		//Delete Button working steps and Design
		
		// 1. Check if a row is selected
		// 2. Find the ID of the selected row index 0
		// 3. Delete Selected row
		// 4. Reset the quantity using the ID
		
		JButton Delete = new JButton("Delete");
		Delete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        DefaultTableModel dt = (DefaultTableModel) table.getModel();

		        int selectedRow = table.getSelectedRow(); // 1
		        if (selectedRow == -1) {
		            // If no row is selected, show an error message
		            JOptionPane.showMessageDialog(frmMenu, "Please select a row to delete.", "Error", JOptionPane.ERROR_MESSAGE);
		            return; // Exit the method
		        }

		        String r = dt.getValueAt(selectedRow, 0).toString(); // 2

		        
		        dt.removeRow(selectedRow); // 3

		        // 4 
		        switch (r) {
		            case "1":
		                q1.setText("0");
		                break;
		            case "2":
		                q2.setText("0");
		                break;
		            case "3":
		                q3.setText("0");
		                break;
		            case "4":
		                q4.setText("0");
		                break;
		            case "5":
		                q5.setText("0");
		                break;
		            case "6":
		                q6.setText("0");
		                break;
		            case "7":
		                q7.setText("0");
		                break;
		            case "8":
		                q8.setText("0");
		                break;
		            case "9":
		                q9.setText("0");
		                break;
		            case "10":
		                q10.setText("0");
		                break;
		            case "11":
		                q11.setText("0");
		                break;
		            case "12":
		                q12.setText("0");
		                break;
		            default:
		                System.out.println("Over");
		        }
		        cal();
		    }
		});

		Delete.setFont(new Font("Tahoma", Font.BOLD, 16));
		Delete.setBounds(777, 441, 97, 34);
		frmMenu.getContentPane().add(Delete);
		
		//Next Button working steps and Design
		// 1. Save the content of table into file, 
		// 2. Make an object of Payment class passing parameters in them for initialization,
		// 3. Make the Payment frame visible 
		// 4. Disposing the Menu Frame
			
		JButton jbtnNext = new JButton("Next->");
		jbtnNext.setForeground(new Color(64, 224, 208));
		jbtnNext.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            saveItemsToFile(); // 1

		            if (table.getRowCount() == 0) {
		                // Show a message indicating that the table is empty
		                JOptionPane.showMessageDialog(frmMenu, "Please add items to the table before proceeding.", "Empty Table", JOptionPane.WARNING_MESSAGE);
		            } else {
		                JOptionPane.showMessageDialog(frmMenu, "Data saved successfully."); // If table is not empty

		                Payment payment = new Payment(getSubtotalValue(), getTotalValue(), getTaxValue()); // 2

		                payment.getFrame().setVisible(true); // 3

		                frmMenu.dispose(); // 4
		            }
		        } catch (Exception ex) {
		            ex.printStackTrace(); // Print the exception trace
		        }
		    }
		});

		
		jbtnNext.setFont(new Font("Tahoma", Font.BOLD, 16));
		jbtnNext.setBounds(773, 526, 101, 36);
		frmMenu.getContentPane().add(jbtnNext);
		
		JLabel lblmenu = new JLabel("---POS SYSTEM MENU---");
		lblmenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblmenu.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblmenu.setBounds(268, 21, 388, 48);
		frmMenu.getContentPane().add(lblmenu);

	}
}
