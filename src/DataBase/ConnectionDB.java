package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
	
	public static Connection connexion(){
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException ex1) {
			System.out.println("Pilote non trouve!");
			System.exit(1);
		}
		try {
			Connection con=DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/entrepcommercial",
			"root","");
			return con;
			
		} catch (SQLException ex2) {
			System.out.println("Erreur JDBC: "+ex2);
			System.exit(1);
			}
		return null;
		
	}
	/**
	 * Fermer une connexion ouverte
	 */
	public static void Deconnexion(Connection con){
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
