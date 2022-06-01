package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import DataBase.ArticleDB;
import module.Article;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddArticleWin extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField labelle;
	private JTextField prixHT;
	private JTextField tva;
	private JTextField quantite;
	private JTextField idArticle;
	
	public AddArticleWin() {

		setTitle("Article");
		setAlwaysOnTop(true);
		getContentPane().setLayout(null);

	
		this.setSize(450, 300);
		this.setLocationRelativeTo(null); 
		
//		get data from data bases if you want to modify the article 
		
		
		JLabel lblNewLabel_5 = new JLabel("Article id :  ");
		lblNewLabel_5.setBounds(10, 39, 121, 14);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel = new JLabel("labelle ");
		lblNewLabel.setBounds(10, 64, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("prix hors taxe (dh)");
		lblNewLabel_1.setBounds(10, 89, 107, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TVA (%)");
		lblNewLabel_2.setBounds(10, 114, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantite dans le stock");
		lblNewLabel_3.setBounds(10, 139, 121, 14);
		getContentPane().add(lblNewLabel_3);
		
		labelle = new JTextField();
		labelle.setBounds(137, 61, 150, 20);
		getContentPane().add(labelle);
		labelle.setColumns(10);
		
		prixHT = new JTextField();
		prixHT.setBounds(137, 86, 150, 20);
		getContentPane().add(prixHT);
		prixHT.setColumns(10);
		
		tva = new JTextField();
		tva.setBounds(137, 111,150, 20);
		getContentPane().add(tva);
		tva.setColumns(10);
		
		quantite = new JTextField();
		quantite.setBounds(137, 136, 150, 20);
		getContentPane().add(quantite);
		quantite.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Annuler");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(319, 227, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Enregistrer");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Article article = new Article(
						Integer.parseInt(idArticle.getText()),
						Integer.parseInt(prixHT.getText()),
						Integer.parseInt(tva.getText()),
						Integer.parseInt(quantite.getText()),
						labelle.getText()
						);
				ArticleDB.ajouterArticle(article);
				JOptionPane.showMessageDialog(null, "enregister avec succes");
				dispose();
			}
		});
		btnNewButton_1.setBounds(211, 227, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		idArticle = new JTextField();
		idArticle.setBounds(137, 36, 150, 20);
		getContentPane().add(idArticle);
		idArticle.setColumns(10);
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
}
