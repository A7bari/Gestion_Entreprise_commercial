package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

import DataBase.ArticleDB;
import DataBase.ClientDB;
import DataBase.CommandeDB;
import DataBase.LigneCommandeDB;
import UI.tabPanels.LigneCommandePan;
import module.Article;
import module.Client;
import module.Commande;
import module.LigneCommande;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddCommande extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idCommande;
	private JTextField articleQ;
	private ArrayList<Client> clients = ClientDB.getClients();
	private ArrayList<Article> articles = ArticleDB.getArticles();
	private ArrayList<Article> articlesInser = new ArrayList<>();
	private ArrayList<LigneCommande> lignCmdList = new ArrayList<>();
	private LigneCommandePan lignecmd = new LigneCommandePan();
	
	public AddCommande() {
		
		this.setSize(562, 596);
		setTitle("Commande");
		getContentPane().setLayout(null);
		lignecmd.setBounds(10, 276, 524, 236);
		
		getContentPane().add(lignecmd);
		
		JLabel lblNewLabel = new JLabel("id de commande *");
		lblNewLabel.setBounds(19, 51, 185, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("client ");
		lblNewLabel_2.setBounds(19, 80, 79, 14);
		getContentPane().add(lblNewLabel_2);
		
		idCommande = new JTextField();
		idCommande.setBounds(141, 48, 179, 20);
		getContentPane().add(idCommande);
		idCommande.setColumns(10);
		
		JComboBox listClients = new JComboBox();
		listClients.setModel(new DefaultComboBoxModel(clientsNonList()));
		listClients.setBounds(141, 76, 179, 22);
		getContentPane().add(listClients);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "ajouter ligne de commande", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 145, 462, 69);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("article :");
		lblNewLabel_1.setBounds(22, 28, 46, 14);
		panel.add(lblNewLabel_1);
		
		JComboBox ListArticles = new JComboBox(new DefaultComboBoxModel(articlesNonList()));
		ListArticles.setBounds(78, 24, 84, 22);
		panel.add(ListArticles);
		
		JLabel lblNewLabel_4 = new JLabel("quantite :");
		lblNewLabel_4.setBounds(183, 28, 79, 14);
		panel.add(lblNewLabel_4);
		
		articleQ = new JTextField();
		articleQ.setBounds(237, 25, 86, 20);
		panel.add(articleQ);
		articleQ.setColumns(10);
		
		// ajouter ligne de commande
		JButton ajouterArt = new JButton("ajouter");
		ajouterArt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ind = ListArticles.getSelectedIndex();
				Article art = articles.get(ind);
				// si l'article selectionne n'exixte deja 
				if (!articlesInser.contains(art)) {
					articlesInser.add(articles.get(ind));
					LigneCommande lc = new LigneCommande(
							ind,
							Integer.parseInt(articleQ.getText()),
							articles.get(ind).getId()
							);
					lignCmdList.add(lc);
					lignecmd.actualiserTable(lignCmdList);
					getContentPane().revalidate();
					getContentPane().repaint();
					articleQ.setText("");					
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "l'acticle selectionnee est deja existe dans la table des lignes de commandes ! ");
				}
			}
		});
		ajouterArt.setBounds(349, 24, 89, 23);
		panel.add(ajouterArt);
		
		JButton btnNewButton_1 = new JButton("annuler");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnNewButton_1.setBounds(447, 523, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		// enregistrement 
		JButton btnNewButton_2 = new JButton("enregistrer");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 1 -calcule de montant
				int mantant = calculMontant();
				// 2- virefier encours si la commande est acceptable
				Client clt = clients.get(listClients.getSelectedIndex());
				
				if (clt.getEncoure() <= 0.1*clt.getChiffreAffaire()) {
					// 3- virefier le stock et mette la commande a une etat (confirmee ou enAttend)
					Boolean confirmee = true;
					for (int i = 0; i < articlesInser.size() ; i++) {
						if (articlesInser.get(i).getQuantiteStock() < lignCmdList.get(i).getQuantite()) {
							confirmee = false;
							// afficher msg 
							JOptionPane.showMessageDialog(null, articlesInser.get(i).getLibelle() + " est manquant la commande va etre en attend .");
							break;
						}
					}
					// si la commende est confermee en mettre a jours le stock
					if (confirmee) {
						for (int i = 0; i < articlesInser.size() ; i++) {
							Article art = articlesInser.get(i);
							art.setQuantiteStock(art.getQuantiteStock() - lignCmdList.get(i).getQuantite());
							ArticleDB.modifierArticle(art);
						}
						JOptionPane.showMessageDialog(null, "le stock est suffisant, la commande est confermee !");
					} 
					
					// verifier que id n'est pas null
					String idS = idCommande.getText();
					if(idS != null) {
						int id = Integer.parseInt(idS);
						Commande cmd = new Commande(
								id,
								mantant,
								false,
								clt.getId(),
								!confirmee,
								confirmee,
								false,
								"",
								null
								);
						
						// mettre a jours l'encours et le chiffre d'affaire
						clt.setEncoure(clt.getEncoure() + mantant);
						clt.setChiffreAffaire(clt.getChiffreAffaire() + mantant);
						ClientDB.modifierClient(clt.getId(), clt);
						
						
						CommandeDB.ajouterCommande(cmd);
						// ajouter les ligne de commande 
						for (LigneCommande lc : lignCmdList) {
							LigneCommandeDB.ajouterLigneCommande(id, lc);
						}
						JOptionPane.showMessageDialog(null, "enregister avec succes");
						// ajouter commande DB
						
					}
				}
				else 
				{
					// la commade va etre refusee
					JOptionPane.showMessageDialog(null, "la commande est refuse encours depace 10% de ca ");
				}
				dispose();
				
			}
		});
		btnNewButton_2.setBounds(304, 523, 116, 23);
		getContentPane().add(btnNewButton_2);
		//=============================================================
		//
		
		// btn ajouter un client 
		JButton nvClient = new JButton("neveau client ");
		nvClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddClientWin();
			}
		});
		nvClient.setBounds(340, 76, 132, 23);
		getContentPane().add(nvClient);
		
		//==========================================================
		JButton suppLignCmd = new JButton("supprimer la ligne");
		suppLignCmd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ind = ListArticles.getSelectedIndex();
				// si l'article selectionne n'exixte deja 
				if (ind > -1) {
					articlesInser.remove(ind);
					lignCmdList.remove(ind);
					lignecmd.actualiserTable(lignCmdList);
					getContentPane().revalidate();
					getContentPane().repaint();					
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "sectionnez la ligne que vous voulez supprimer ! ");
				}
			}
		});
		suppLignCmd.setBounds(10, 243, 116, 23);
		getContentPane().add(suppLignCmd);
		
		this.setVisible(true);
	}
	
	// les noms des article non ajouter 
	private String[] articlesNonList() {
		String [] lst = new String[articles.size()];
		for (int i = 0; i < lst.length; i++) {
			lst[i] = articles.get(i).getLibelle();
		}
		return lst;
	}
	private String[] clientsNonList() {
		String [] lst = new String[clients.size()];
		for (int i = 0; i < lst.length; i++) {
			lst[i] = clients.get(i).getNom();
		}
		return lst;
	}
	private int calculMontant() {
		int res = 0 ;
		for (int i = 0; i < lignCmdList.size(); i++) {
			res += ((articlesInser.get(i).getTVA() / 100 ) +  articlesInser.get(i).getPrixHT()) * lignCmdList.get(i).getQuantite();
		}
		return res;
	}
}
