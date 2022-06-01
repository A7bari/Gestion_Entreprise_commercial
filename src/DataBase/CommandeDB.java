package DataBase;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import module.Commande;


public class CommandeDB {
	
	public static void ajouterCommande(Commande cmd){

		Connection con=ConnectionDB.connexion();
		String sql ="INSERT INTO commande (	idCommande,	montant, regler, client, enAttend, confirmer, livrer, dateLiv ) VALUES (?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cmd.getIdCommande());
			pst.setInt(2, cmd.getMontant());
			pst.setBoolean(3, cmd.isRegler());
			pst.setInt(4, cmd.getClient());
			pst.setBoolean(5, cmd.isEnAttend());
			pst.setBoolean(6, cmd.isConfermer());
			pst.setBoolean(7, cmd.isLivrer());
			pst.setString(8, cmd.getDateLiv());
			
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
	}

	public static void supprimerCommande(int idCommande){
		Connection con=ConnectionDB.connexion();
		String sql="DELETE FROM commande WHERE commande.idCommande=? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idCommande);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnectionDB.Deconnexion(con);
	}
	
	public static void modifierCommande(Commande cmd){
		Connection con=ConnectionDB.connexion();
		String sql= "UPDATE commande SET montant=?, regler=?, enAttend = ?, confirmer = ?, livrer = ? , dateLiv=? WHERE commande.idCommande=? " ;
		try {

			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, cmd.getMontant());
			pst.setBoolean(2, cmd.isRegler());
			pst.setBoolean(3, cmd.isEnAttend());
			pst.setBoolean(4, cmd.isConfermer());
			pst.setBoolean(5, cmd.isLivrer());
			pst.setString(6, cmd.getDateLiv());
			pst.setInt(7, cmd.getIdCommande());

			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnectionDB.Deconnexion(con);
	}
	
	public static ArrayList<Commande> getCommandes(){
		Connection con=ConnectionDB.connexion();
		ArrayList<Commande> listeCommande = new ArrayList<Commande>();
		String sql = "SELECT * FROM commande ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				Commande cmd = new Commande(
						res.getInt("idCommande"), 
						res.getInt("montant"),
						res.getBoolean("regler"),
						res.getInt(4),
						res.getBoolean(5),
						res.getBoolean(6),
						res.getBoolean(7),
						res.getString(8),
						null
						);
				listeCommande.add(cmd);
				}
			return listeCommande;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return null;
	}
	public static Commande getCommande(int id){
		Connection con=ConnectionDB.connexion();
		String sql = "SELECT * FROM commande WHERE commande.idCommande=" + id + " ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			Commande cmd = new Commande(
					id, 
					res.getInt("montant"),
					res.getBoolean("regler"),
					res.getInt(4),
					res.getBoolean(5),
					res.getBoolean(6),
					res.getBoolean(7),
					res.getString(8),
					null
					);
			return cmd;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return null;
	}
	
	public static ArrayList<Commande> getCommandesEnAtt(){
		Connection con=ConnectionDB.connexion();
		ArrayList<Commande> listeCommande = new ArrayList<Commande>();
		String sql = "SELECT * FROM commande WHERE commande.enAttend = 1 ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				Commande cmd = new Commande(
						res.getInt("idCommande"),
						res.getInt("montant"),
						res.getBoolean("regler"),
						res.getInt(4),
						res.getBoolean(5),
						res.getBoolean(6),
						res.getBoolean(7),
						res.getString(8),
						null
						);
				
				
				listeCommande.add(cmd);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return listeCommande;
	}
	public static ArrayList<Commande> getCommandesConf(){
		Connection con=ConnectionDB.connexion();
		ArrayList<Commande> listeCommande = new ArrayList<Commande>();
		String sql = "SELECT * FROM commande WHERE commande.confirmer = 1 ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				Commande cmd = new Commande(
						res.getInt("idCommande"), 
						res.getInt("montant"),
						res.getBoolean("regler"),
						res.getInt(4),
						res.getBoolean(5),
						res.getBoolean(6),
						res.getBoolean(7),
						res.getString(8),
						null
						);
				
				
				listeCommande.add(cmd);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return listeCommande;
	}
	
	public static ArrayList<Commande> getCommandesLiv(){
		Connection con=ConnectionDB.connexion();
		ArrayList<Commande> listeCommande = new ArrayList<Commande>();
		String sql = "SELECT * FROM commande WHERE commande.livrer = 1 ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				Commande cmd = new Commande(
						res.getInt("idCommande"), 
						res.getInt("montant"),
						res.getBoolean("regler"),
						res.getInt(4),
						res.getBoolean(5),
						res.getBoolean(6),
						res.getBoolean(7),
						res.getString(8),
						null
						);
				
				
				listeCommande.add(cmd);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return listeCommande;
	}
	
	
}















