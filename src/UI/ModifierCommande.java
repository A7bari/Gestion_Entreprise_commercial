package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import DataBase.ArticleDB;
import DataBase.ClientDB;
import DataBase.CommandeDB;
import DataBase.LigneCommandeDB;
import module.Article;
import module.Client;
import module.Commande;
import module.LigneCommande;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ModifierCommande extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1842294968427319461L;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	public ModifierCommande(Commande cmd ) {
		setTitle("modifier commande");
		
		getContentPane().setLayout(null);
		setSize(420, 276);
		JLabel cmdid = new JLabel("commande id :       " + cmd.getIdCommande());
		cmdid.setBounds(20, 23, 123, 14);
		getContentPane().add(cmdid);
		
		JLabel lblNewLabel = new JLabel("Montant :          " + cmd.getMontant());
		lblNewLabel.setBounds(20, 48, 107, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("client :       " + cmd.getClient());
		lblNewLabel_1.setBounds(20, 73, 107, 14);
		getContentPane().add(lblNewLabel_1);
		
		JRadioButton enAtt = new JRadioButton("en attend");
		enAtt.setSelected(cmd.isEnAttend());
		buttonGroup.add(enAtt);
		enAtt.setBounds(81, 123, 90, 23);
		getContentPane().add(enAtt);
		
		JRadioButton cnf = new JRadioButton("confirmer");
		buttonGroup.add(cnf);
		cnf.setBounds(178, 123, 82, 23);
		cnf.setSelected(cmd.isConfermer());
		getContentPane().add(cnf);
		
		JRadioButton liv = new JRadioButton("livrer");
		buttonGroup.add(liv);
		liv.setBounds(262, 123, 109, 23);
		liv.setSelected(cmd.isLivrer());
	
		
		getContentPane().add(liv);
		
		JLabel lblNewLabel_2 = new JLabel("etat : " + cmd.getEtat());
		lblNewLabel_2.setBounds(20, 127, 55, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("enregistrer");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (cmd.isLivrer()) {
					// si la commande est deja livrer on peut pas la modifier 
					JOptionPane.showMessageDialog(null, "deja livrer on peut pas la modifier !");
				} 
				else 
				{
					if (cmd.isConfermer() && (enAtt.getSelectedObjects() != null) ) {
						// en peut pas aller de etat confermer a l'etat en attend 
						JOptionPane.showMessageDialog(null, "en peut pas mettre en attend une commande deja confermee !");
					}
					else {
						// si la commande rend a l'etat confirmee ou livrer , en mettre a jours les stock
						if ((cnf.getSelectedObjects()!= null) || (liv.getSelectedObjects()!= null)) {
							ArrayList<LigneCommande> lcs = LigneCommandeDB.getLigneCommandes(cmd.getIdCommande());
							ArrayList<Article> arts = new ArrayList<>();
							boolean confStock = true;
							// verifier le stock
							for (LigneCommande lc : lcs) {
								Article art = ArticleDB.getArticle(lc.getArticle());
								if(lc.getQuantite() > art.getQuantiteStock()) {
									JOptionPane.showMessageDialog(null, "le stock est insuffisant pour mettre la commande confirmee (livree) !");
									confStock = false ;
								}
							}
							// si le stock est suff
							if (confStock) {
								for (int i = 0 ; i < arts.size(); i++  ) {
									arts.get(i).setQuantiteStock(arts.get(i).getQuantiteStock() - lcs.get(i).getQuantite());
									ArticleDB.modifierArticle(arts.get(i));
								}								
							}
					
							// si la commande rend a l'etat livree, en mettre l'encoure a jours
							if (liv.getSelectedObjects()!= null) {
								// la commande est livree alors elle est regler
								cmd.setRegler(true);
								// obtenir le client et modifier l'encours et le chiffre d'affaire
								Client clt = ClientDB.getClient(cmd.getClient());
								clt.setEncoure(clt.getEncoure() - cmd.getMontant());
								ClientDB.modifierClient(clt.getId(), clt);
								
								//mettre la commande regler 
							}	
							cmd.setEnAttend(enAtt.getSelectedObjects() != null);
							cmd.setConfermer(cnf.getSelectedObjects() != null);
							cmd.setLivrer(liv.getSelectedObjects()!= null);
							CommandeDB.modifierCommande(cmd);
						}
					}
				}
				dispose();
			}
		});
		btnNewButton.setBounds(262, 189, 109, 23);
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
}
