package UI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JTextField;


import DataBase.ArticleDB;
import DataBase.ClientDB;
import DataBase.CommandeDB;
import DataBase.LigneCommandeDB;
import UI.tabPanels.LigneCommandePan;
import module.Article;
import module.Client;
import module.Commande;
import module.LigneCommande;

public class CommandeDetails extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2974121249312788119L;
	private JTextField idCommande;
	private Client client;
	private Commande commande;
	private ArrayList<Article> articles = new ArrayList<>() ;
	private ArrayList<LigneCommande> lignCmdList;
	private LigneCommandePan lignecmd;
	private JTextField textField;
	private JTextField clientid;
	
	public CommandeDetails(int id) {
		
		this.setSize(562, 596);
		setTitle("Details de Commande");
		getContentPane().setLayout(null);

		
		// get commande et client et lignes de commande et leurs articles
		commande = CommandeDB.getCommande(id);
		client = ClientDB.getClient(commande.getClient());
		lignCmdList = LigneCommandeDB.getLigneCommandes(id);
		for (LigneCommande lc : lignCmdList) {
			articles.add(ArticleDB.getArticle(lc.getArticle()));
		}
		
		// creer panel pour afficher les lignes de commandes 
		lignecmd = new LigneCommandePan(lignCmdList);
		lignecmd.setBounds(12, 233, 524, 279);
		lignecmd.desableTable();
		
		getContentPane().add(lignecmd);
		
		JLabel lblNewLabel = new JLabel("id de commande *");
		lblNewLabel.setBounds(19, 51, 185, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("client ");
		lblNewLabel_2.setBounds(19, 80, 79, 14);
		getContentPane().add(lblNewLabel_2);
		
		idCommande = new JTextField(id+"");
		idCommande.setEditable(false);
		idCommande.setBounds(141, 48, 179, 20);
		getContentPane().add(idCommande);
		idCommande.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("fermer");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(447, 523, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		textField = new JTextField(client.getNom());
		textField.setEditable(false);
		textField.setBounds(141, 76, 179, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("client id");
		lblNewLabel_1.setBounds(19, 105, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		clientid = new JTextField(client.getId()+"");
		clientid.setEditable(false);
		clientid.setBounds(141, 107, 179, 20);
		getContentPane().add(clientid);
		clientid.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("reglement :   " + commande.isRegler());
		lblNewLabel_3.setBounds(19, 183, 137, 14);
		getContentPane().add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_4 = new JLabel("etat :        " + commande.getEtat());
		lblNewLabel_4.setBounds(19, 208, 137, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("montant :       " + commande.getMontant());
		lblNewLabel_5.setBounds(19, 160, 125, 14);
		getContentPane().add(lblNewLabel_5);
		
		this.setVisible(true);
	}
}
