package UI.tables;

import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DataBase.ClientDB;
import module.Client;
import module.TableInterface;

import javax.swing.ListSelectionModel;


public class ClientTable extends JTable implements TableInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4439645528965962484L;
	private ArrayList<Client> clients = ClientDB.getClients();
	private DefaultTableModel model ;
	
	public ClientTable() {
		setShowGrid(false);
//		setSize(700,400);
		setFillsViewportHeight(true);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		model = createModel();
		setModel(model);
	}
	
	public Client getClient(int indexLigne){
		return clients.get(indexLigne);
	}

	@Override
	public void modifier() {
		int ligne = this.getSelectedRow();
		if (ligne != -1) {
			Client clt = clients.get(ligne);
	
			boolean isCompany = this.getModel().getValueAt(ligne, 6) == "entreprise";
			Client nvClt = new Client(
					Integer.parseInt(getModel().getValueAt(ligne, 0).toString()),
					this.getModel().getValueAt(ligne, 1).toString(),
					isCompany,
					Integer.parseInt(getModel().getValueAt(ligne, 2).toString()),
					Integer.parseInt(getModel().getValueAt(ligne, 3).toString()),
					this.getModel().getValueAt(ligne, 4).toString(),
					Integer.parseInt(getModel().getValueAt(ligne, 5).toString())
					);
			
			ClientDB.modifierClient(clt.getId(), nvClt);
			actualiser();	
		}
	}

	@Override
	public void actualiser() {
		clients = ClientDB.getClients();
		model = createModel();
		this.setModel(model);
	}

	@Override
	public void supprimer() {
		int ligne = this.getSelectedRow();
		if (ligne != -1) {
			Client clt = clients.get(ligne);
			ClientDB.supprimerClient(clt.getId());
			actualiser();			
		}
	}

	@Override
	public void filtrer(int id) {
		DefaultTableModel model = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"id", "nom", "encours", "chiffre d'affaire", "adresse de livraison", "tel", "type"
				}
			);
			for (Client client : clients) {
				if(client.getId() == id) {
					String type = (client.isEstEntreprise())? "entreprise" : "personne";
					model.addRow(new Object[] {client.getId(), client.getNom(), client.getEncoure(), client.getChiffreAffaire(), client.getAdresseLiv(), client.getTel(), type});
				}
			}
		this.setModel(model);
	}

	private DefaultTableModel createModel() {
		DefaultTableModel model = new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "nom", "encours", "chiffre d'affaire", "adresse de livraison", "tel", "type"
			}
		);
		for (Client client : clients) {

			String type = (client.isEstEntreprise())? "entreprise" : "personne";
			model.addRow(new Object[] {client.getId(), client.getNom(), client.getEncoure(), client.getChiffreAffaire(), client.getAdresseLiv(), client.getTel(), type});
			
		}
		return model;
	}
}
