package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import module.LigneCommande;

public class LigneCommandeDB {
	
	public static void ajouterLigneCommande(int idCommande ,LigneCommande lc) {
		Connection con=ConnectionDB.connexion();
		String sql ="INSERT INTO lignecommande (idLigneCommande, quantite, article , commande ) VALUES (?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, lc.getId());
			pst.setInt(2, lc.getQuantite());
			pst.setInt(3, lc.getArticle());
			pst.setInt(4, idCommande);
			
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
	}
	public static void supprimerLigneCommande(int id ) {
		Connection con=ConnectionDB.connexion();
		String sql ="DELETE FROM lignecommande WHERE lignecommande.idLigneCommande=? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
	}
	public static ArrayList<LigneCommande> getLigneCommandes(){
		Connection con=ConnectionDB.connexion();
		ArrayList<LigneCommande> listeCL= new ArrayList<LigneCommande>();
		String sql = "SELECT * FROM lignecommande ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				LigneCommande lc = new LigneCommande(
						res.getInt(2), 
						res.getInt(3),
						res.getInt(4)
						);
				
				
				listeCL.add(lc);
				}
			return listeCL;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return null;
	}
	public static ArrayList<LigneCommande> getLigneCommandes(int idCommande) {
		Connection con=ConnectionDB.connexion();
		ArrayList<LigneCommande> listeCL= new ArrayList<LigneCommande>();
		String sql = "SELECT * FROM lignecommande WHERE  lignecommande.commande = "+ idCommande;
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				LigneCommande lc = new LigneCommande(
						res.getInt(2), 
						res.getInt(3),
						res.getInt(4)
						);
				
				
				listeCL.add(lc);
				}
			return listeCL;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return null;
	}
}















