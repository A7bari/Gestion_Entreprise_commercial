package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import DataBase.ClientDB;
import module.Client;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddClientWin extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nomF;
	private JTextField adresseLivF;
	private JTextField adresseFacturF;
	private JTextField telF;
	private JTextField emailF;
	private JTextField idClient;
	private JRadioButton isCompanyRBtn;
	
	public AddClientWin() {
		
		setAlwaysOnTop(true);
		setTitle("Client");
		getContentPane().setLayout(null);
		
		this.setSize(610, 420);
		this.setLocationRelativeTo(null); 
		
		JLabel lblNewLabel = new JLabel("Client id :   ");
		lblNewLabel.setBounds(26, 80, 117, 14);
		getContentPane().add(lblNewLabel);
		
		isCompanyRBtn = new JRadioButton("Entreprise ? ");
		isCompanyRBtn.setSelected(true);
		isCompanyRBtn.setBounds(26, 37, 109, 23);
		getContentPane().add(isCompanyRBtn);
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(471, 347, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enregistrer");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Client client = new Client(
						Integer.parseInt( idClient.getText()),
						nomF.getText(),
						(isCompanyRBtn.getSelectedObjects() != null),
						0,
						0,
						adresseLivF.getText(),
						Integer.parseInt(telF.getText())
						);
				
				ClientDB.ajouterClient(client);
				JOptionPane.showMessageDialog(null, "enregister avec succes");
				dispose();
			}
		});
		btnNewButton_1.setBounds(342, 347, 94, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("nom");
		lblNewLabel_1.setBounds(26, 107, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		nomF = new JTextField();
		nomF.setBounds(175, 104, 222, 20);
		
		getContentPane().add(nomF);
		nomF.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("adresse de livraison ");
		lblNewLabel_9.setBounds(26, 135, 152, 14);
		getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("adresse de facturation");
		lblNewLabel_10.setBounds(26, 160, 143, 14);
		getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("telephone");
		lblNewLabel_11.setBounds(26, 185, 99, 14);
		getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("email");
		lblNewLabel_12.setBounds(26, 210, 46, 14);
		getContentPane().add(lblNewLabel_12);
		
		adresseLivF = new JTextField();
		adresseLivF.setBounds(175, 132, 222, 20);
		getContentPane().add(adresseLivF);
		adresseLivF.setColumns(10);
		
		adresseFacturF = new JTextField();
		adresseFacturF.setBounds(175, 157, 222, 20);
		getContentPane().add(adresseFacturF);
		adresseFacturF.setColumns(10);
		
		telF = new JTextField();
		telF.setBounds(175, 182, 222, 20);
		getContentPane().add(telF);
		telF.setColumns(10);
		
		emailF = new JTextField();
		emailF.setBounds(175, 207, 222, 20);
		getContentPane().add(emailF);
		emailF.setColumns(10);
		
		idClient = new JTextField();
		idClient.setBounds(175, 77, 222, 20);
		getContentPane().add(idClient);
		idClient.setColumns(10);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
