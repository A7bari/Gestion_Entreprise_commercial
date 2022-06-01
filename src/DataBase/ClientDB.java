package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import module.Client;


public class ClientDB {
	
	
	public static void ajouterClient(Client clt){
		Connection con=ConnectionDB.connexion();
		String sql ="INSERT INTO client (idClient, nom, estEntreprise	,encoure ,chiffreAffaire, adresseLiv, tel) VALUES (?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, clt.getId());
			pst.setString(2, clt.getNom());
			pst.setBoolean(3, clt.isEstEntreprise());
			pst.setInt(4, clt.getEncoure());
			pst.setInt(5, clt.getChiffreAffaire());
			pst.setString(6, clt.getAdresseLiv());
			pst.setInt(7, clt.getTel());
			
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
	}

	public static void supprimerClient(int idClient){
		Connection con=ConnectionDB.connexion();
		String sql="DELETE FROM client WHERE client.idClient=? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idClient);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnectionDB.Deconnexion(con);
	}
	
	public static void modifierClient(int idClient, Client clt){
		Connection con=ConnectionDB.connexion();
		String sql= "UPDATE client SET  nom=?, estEntreprise=?	,encoure=? ,chiffreAffaire=?, adresseLiv=?, tel=?  WHERE idClient=?" ;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, clt.getNom());
			pst.setBoolean(2, clt.isEstEntreprise());
			pst.setInt(3, clt.getEncoure());
			pst.setInt(4, clt.getChiffreAffaire());
			pst.setString(5, clt.getAdresseLiv());
			pst.setInt(6, clt.getTel());
			pst.setInt(7, idClient);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnectionDB.Deconnexion(con);
	}

	public static ArrayList<Client> getClients() {
		Connection con=ConnectionDB.connexion();
		ArrayList<Client> listeClient= new ArrayList<Client>();
		String sql = "SELECT * FROM client ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				Client clt = new Client(
						res.getInt(2), 
						res.getString(3),
						res.getBoolean(4),
						res.getInt(5),
						res.getInt(6),
						res.getString(7),
						res.getInt(8)
						);
				
				
				listeClient.add(clt);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return listeClient;
	}


	public static Client getClient(int id) {
		Connection con=ConnectionDB.connexion();
		String sql = "SELECT * FROM client WHERE client.idClient= "+ id;
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			res.next();
				
			Client clt = new Client(
					res.getInt(2), 
					res.getString(3),
					res.getBoolean(4),
					res.getInt(5),
					res.getInt(6),
					res.getString(7),
					res.getInt(8)
				);
			return clt;
				
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		ConnectionDB.Deconnexion(con);
		return null;
	
	}
	
}
