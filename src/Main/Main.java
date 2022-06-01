package Main;

import javax.swing.UIManager;
import UI.mainWindow;



/*
*
*   ce projet est réalisé par l'étudiant:
*   
*	Mohammed Ahbari   N: 1  G.INFO 1.
*	29/05/2022
*
*/


public class Main {

	public static void main(String[] args) {
		
//		essay d'implimenter un theme :
		try {
			UIManager.setLookAndFeel(
		            UIManager.getSystemLookAndFeelClassName());
//			UIManager.setLookAndFeel( new FlatLightLaf() );
			} catch (Exception e) {
              System.err.println("Look and feel not set.");
         }
		 new mainWindow();
	

	}

}
