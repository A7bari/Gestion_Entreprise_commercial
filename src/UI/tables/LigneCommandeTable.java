package UI.tables;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DataBase.ArticleDB;
import DataBase.LigneCommandeDB;
import module.Article;
import module.LigneCommande;
import module.TableInterface;

public class LigneCommandeTable extends JTable implements TableInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -734537505255180561L;
	private ArrayList<LigneCommande> ligneCommandes = new ArrayList<>();
	private DefaultTableModel model ;
	
	public LigneCommandeTable() {
		
		setFillsViewportHeight(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = createModel();
		setModel(model);

	}
	private DefaultTableModel createModel() {
		DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"article id", "article nom", "quantiter demander"
			}
		);
		if (ligneCommandes != null) {
			for (LigneCommande lc : ligneCommandes) {
				Article art = ArticleDB.getArticle(lc.getArticle());
				model.addRow(new Object[] {lc.getId(), art.getLibelle(), lc.getQuantite()});
			}
		}
		return model;
	}
	
	public LigneCommandeTable(ArrayList<LigneCommande> lignCmdList) {
			ligneCommandes = lignCmdList;
			setFillsViewportHeight(true);
			setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			model = createModel();
			setModel(model);
	
		}
	
	@Override
	public void modifier() {

	}

	@Override
	public void actualiser() {
		ligneCommandes = LigneCommandeDB.getLigneCommandes();
		model = createModel();
		setModel(model);

	}

	@Override
	public void supprimer() {
		int ligne = this.getSelectedRow();
		if (ligne != -1) {
			LigneCommande lc = ligneCommandes.get(ligne);
			LigneCommandeDB.supprimerLigneCommande(lc.getId());
			actualiser();			
		}

	}

	@Override
	public void filtrer(int id) {
	}
	
	public void setLigneCommandes(ArrayList<LigneCommande> lst) {
		this.ligneCommandes = lst;
		model = createModel();
		setModel(model);
	}
	public void desable() {
		this.setEnabled(false);
		
	}
}
