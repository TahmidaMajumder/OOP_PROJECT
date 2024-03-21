import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;

public class Welcome_scrn {

	private JFrame frame;

	// Main Method For The Project
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome_scrn window = new Welcome_scrn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Welcome_scrn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame. This is welcome screen
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 900, 612);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// This Button Takes To Menu Page.
		// 1. Creating A Menu Object
		// 2. Setting The Menu Frame Visible
		// 3. Disposing the Welcome+scrn Frame
		
		JButton Firstbtn = new JButton("Click here to order");
		Firstbtn.setFont(new Font("Calibri", Font.BOLD, 20));
		Firstbtn.setForeground(new Color(255, 255, 255));
		Firstbtn.setBackground(new Color(85, 85, 85));
		Firstbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu mp = new Menu(); // 1
				mp.frmMenu.setVisible(true); // 2
				frame.dispose(); // 3
			}
		});
		Firstbtn.setBounds(346, 275, 212, 53);
		frame.getContentPane().add(Firstbtn);
		
		JLabel lblNewLabel = new JLabel("**WELCOME  TO POS SYSTEM**");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 24));
		lblNewLabel.setBounds(280, 132, 356, 88);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(getClass().getResource("/pic/Welcome.jpg")));
		lblNewLabel_1.setBounds(0, 0, 884, 573);
		frame.getContentPane().add(lblNewLabel_1);
	}
}
