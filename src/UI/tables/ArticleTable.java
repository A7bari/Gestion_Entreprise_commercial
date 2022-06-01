package UI.tables;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DataBase.ArticleDB;
import module.Article;
import module.TableInterface;

public class ArticleTable extends JTable implements TableInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 99718185871247415L;
	private ArrayList<Article> articles = ArticleDB.getArticles();
	private DefaultTableModel model ;
	
	public ArticleTable() {
		setSize(700,400);
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
					"id",	"libelle",	"prix haut tax",	"TVA",	"quantite dans Stock"
			}
		);
		for (Article art : articles) {
			model.addRow(new Object[] {art.getId(), art.getLibelle(), art.getPrixHT(), art.getTVA(), art.getQuantiteStock()});
		}
		return model;
	}
	
	
	@Override
	public void modifier() {
		int l = this.getSelectedRow();
		if (l != -1) {
			Article art = articles.get(l);
			art.setLibelle(getModel().getValueAt(l, 1).toString());
			art.setPrixHT(Integer.parseInt(getModel().getValueAt(l, 2).toString()));
			art.setTVA(Integer.parseInt(getModel().getValueAt(l, 3).toString()));
			art.setQuantiteStock(Integer.parseInt(getModel().getValueAt(l, 4).toString()));
			
			ArticleDB.modifierArticle(art);
			actualiser();
		}

	}

	@Override
	public void actualiser() {
		articles = ArticleDB.getArticles();
		model = createModel();
		setModel(model);

	}

	@Override
	public String toString() {
		return "ArticleTable [articles=" + articles + ", model=" + model + "]";
	}
	@Override
	public void supprimer() {
		int ligne = this.getSelectedRow();
		if (ligne != -1) {
			Article art = articles.get(ligne);
			ArticleDB.supprimerArticle(art.getId());
			actualiser();			
		}

	}

	@Override
	public void filtrer(int id) {
		DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
					"id",	"libelle",	"prix haut tax",	"TVA",	"quantite dans Stock"
			}
			);
		
			for (Article art : articles) {
				if (art.getId() == id) {
					model.addRow(new Object[] {art.getId(), art.getLibelle(), art.getPrixHT(), art.getTVA(), art.getQuantiteStock()});					
				}
			}
			setModel(model);
	}

}
