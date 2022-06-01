package DataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import module.Article;


public class ArticleDB {
	
	public static void ajouterArticle(Article art){
		Connection con=ConnectionDB.connexion();
		String sql ="INSERT INTO article (	idArticle,	libelle,	prixHT,	TVA,	quantiteStock) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, art.getId());
			pst.setString(2, art.getLibelle());
			pst.setInt(3, art.getPrixHT());
			pst.setInt(4, art.getTVA());
			pst.setInt(5, art.getQuantiteStock());
			
			pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
	}

	public static void supprimerArticle(int idArticle){
		Connection con=ConnectionDB.connexion();
		String sql="DELETE FROM article WHERE article.idArticle=? ";
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, idArticle);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnectionDB.Deconnexion(con);
	}
	
	public static void modifierArticle(Article art){
		Connection con=ConnectionDB.connexion();
		String sql= "UPDATE article SET libelle= ?,	prixHT =?,	TVA=?, quantiteStock= ?  WHERE article.idArticle=? " ;
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			
			pst.setString(1, art.getLibelle());
			pst.setInt(2, art.getPrixHT());
			pst.setInt(3, art.getTVA());
			pst.setInt(4, art.getQuantiteStock());
			pst.setInt(5, art.getId());
			
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		ConnectionDB.Deconnexion(con);
	}
	
	public static ArrayList<Article> getArticles() {
		Connection con=ConnectionDB.connexion();
		ArrayList<Article> listeArticle= new ArrayList<Article>();
		String sql = "SELECT * FROM article ";
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			while (res.next()) {
				
				Article art = new Article(
						res.getInt(2), 
						res.getInt(4),
						res.getInt(5),
						res.getInt(6),
						res.getString(3)
						);
				
				
				listeArticle.add(art);
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return listeArticle;
	}
	public static Article getArticle(int id) {
		Connection con=ConnectionDB.connexion();
		String sql = "SELECT * FROM article WHERE article.idArticle= "+id;
		try {
			java.sql.Statement stmt = con.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			res.next();
			Article art = new Article(
						id, 
						res.getInt(4),
						res.getInt(5),
						res.getInt(6),
						res.getString(3)
						);
			return art;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		ConnectionDB.Deconnexion(con);
		
		return null;
	}

	
}
