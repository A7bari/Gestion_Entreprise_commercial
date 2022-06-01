package UI.tables;

import module.Commande;
import module.TableInterface;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import DataBase.CommandeDB;

public class CommandeTable extends JTable implements TableInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8355010512378012360L;
	DefaultTableModel model;
	ArrayList<Commande> commandes =  CommandeDB.getCommandes();
	
	public CommandeTable() {
		setShowGrid(false);
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
					"id",	"montant",	"reglement",	"client id",	"etat"
			}
		);
		for (Commande cmd : commandes) {
			model.addRow(new Object[] { cmd.getIdCommande(), cmd.getMontant(), cmd.isRegler(), cmd.getClient(), cmd.getEtat() });
		}
		return model;
	}
	@Override
	public void modifier() {

	}

	@Override
	public void actualiser() {
		commandes = CommandeDB.getCommandes();
		model = createModel();
		setModel(model);
		
	}

	@Override
	public void supprimer() {
		// TODO Auto-generated method stub

	}

	@Override
	public void filtrer(int id) {
		// TODO Auto-generated method stub

	}
	public int getSelectedID() {
		int i = this.getSelectedRow();
		return commandes.get(i).getIdCommande();
	}
	public Commande getSelecteCmd() {
		int i = this.getSelectedRow();
		
		return (i == -1)? null: commandes.get(i);
		
	}
	public void cmdEnAtt() {
		commandes = CommandeDB.getCommandesEnAtt();
		model = createModel();
		setModel(model);
	}
	public void cmdConf() {
		commandes = CommandeDB.getCommandesConf();
		model = createModel();
		setModel(model);
	}
	public void cmdLiv() {
		commandes = CommandeDB.getCommandesLiv();
		model = createModel();
		setModel(model);
	}

}
