package sockergroda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sockergroda.enums.Images;

public class RemoveAdvertisements {

	private JFrame frmSockergrodaRemoveAds;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void display() {
		try {
			RemoveAdvertisements window = new RemoveAdvertisements();
			window.frmSockergrodaRemoveAds.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public RemoveAdvertisements() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSockergrodaRemoveAds = new JFrame();
		frmSockergrodaRemoveAds.setResizable(false);
		frmSockergrodaRemoveAds.setTitle("Sockergroda - Remove Advertisements");
		frmSockergrodaRemoveAds.setBounds(100, 100, 385, 185);
		frmSockergrodaRemoveAds.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frmSockergrodaRemoveAds.setIconImage(Images.ICON_1024x1024.getImage());
	    frmSockergrodaRemoveAds.setLocationRelativeTo(null);
	    frmSockergrodaRemoveAds.getContentPane().setLayout(null);
	    
	    JButton btnBack = new JButton("Back");
	    btnBack.setBounds(10, 112, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmSockergrodaRemoveAds.setVisible(false);
				MainWindow.display();
			}
		});
	    frmSockergrodaRemoveAds.getContentPane().add(btnBack);
	    
	    textField = new JTextField();
	    textField.setBounds(10, 52, 86, 20);
	    frmSockergrodaRemoveAds.getContentPane().add(textField);
	    textField.setColumns(10);
	    
	    JLabel lblNewLabel = new JLabel("Enter a key given by the developer to remove advertisements:");
	    lblNewLabel.setBounds(10, 27, 362, 14);
	    frmSockergrodaRemoveAds.getContentPane().add(lblNewLabel);
	    
	    JButton btnRemoveAds = new JButton("Remove Ads");
	    btnRemoveAds.setBounds(10, 83, 130, 23);
	    btnRemoveAds.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(Main.hasRemovedAds()) {
	    			JOptionPane.showMessageDialog(frmSockergrodaRemoveAds, "Advertisements have already been removed.", "Cannot Remove Advertisements", JOptionPane.ERROR_MESSAGE);
	    			return;
	    		}
	    		
	    		String removeAdsKey = textField.getText();
	    		int raKey = 0;
	    		try {
	    			raKey = Integer.parseInt(removeAdsKey);
	    		} catch(NumberFormatException e1) {}
	    		
	    		try {
					if(APIManager.testRAKey(raKey)) {
						Main.removeAds();
						JOptionPane.showMessageDialog(frmSockergrodaRemoveAds, "Success! Advertisements have been removed.", "Advertisements Removed", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(frmSockergrodaRemoveAds, "An invalid RA key was provided. Make sure that you wrote it correctly.", "Invalid Key", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(frmSockergrodaRemoveAds, "An error occured whilst trying to connect to the server. Please try again later.", "Server Error", JOptionPane.ERROR_MESSAGE);
				}
	    	}
	    });
	    frmSockergrodaRemoveAds.getContentPane().add(btnRemoveAds);
	}
}