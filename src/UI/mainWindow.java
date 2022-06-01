package UI;

import java.awt.BorderLayout;


import java.awt.FlowLayout;

import java.awt.GridLayout;

import java.awt.Toolkit;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import UI.tabPanels.ArticlePan;
import UI.tabPanels.ClientPan;
import UI.tabPanels.CommandePan;
import javax.swing.border.EmptyBorder;

public class mainWindow extends JFrame {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JPanel pnlMain ;
	JToolBar toolbar;
	JPanel navBar;
	JPanel content ;
	
	private JPanel activePan;
	private JPanel clientPan = new ClientPan();
	private JPanel articlePan = new ArticlePan();
	private JPanel commandePan = new CommandePan();
	JScrollPane scTable;

	public mainWindow() {
		this.setTitle("gestion d'une entreprise commercial");
		
		// get the screen size 
		int x = Toolkit.getDefaultToolkit().getScreenSize().width ;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		this.setSize(2*x/3, 2*y/3);
		this.setLocationRelativeTo(null);  /// center the window 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		pnlMain = (JPanel) this.getContentPane();
		pnlMain.setLayout( new BorderLayout() );
        
//		===================== tool bar ===============================================
//		==============================================================================
		
        toolbar = new JToolBar();
        pnlMain.add( createToolBar(toolbar), BorderLayout.NORTH );
        
//      ================ Navigation menu =============================================
        //============================================================================
        
        navBar =  new JPanel();
        navBar.setBorder(new EmptyBorder(1, 1, 1, 1));
        pnlMain.add( navBar, BorderLayout.WEST );
        navBar.setLayout(new GridLayout(9, 1, 0, 0));
        
        JButton allCommandes = new JButton("gestion de commandes");
        allCommandes.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		pnlMain.remove(activePan);
                pnlMain.add(commandePan, BorderLayout.CENTER );
                pnlMain.revalidate();
        		pnlMain.repaint();
        		activePan = commandePan;
        	}
        });
        navBar.add(allCommandes);
        
        JButton articles = new JButton("gestion d'articles");
        articles.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		pnlMain.remove(activePan);
                pnlMain.add(articlePan, BorderLayout.CENTER );
                pnlMain.revalidate();
        		pnlMain.repaint();
        		activePan = articlePan;
        	}
        });
        navBar.add(articles);
        
        JButton clients = new JButton("gestion des clients");
        clients.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        	
        		pnlMain.remove(activePan);
                pnlMain.add(clientPan, BorderLayout.CENTER );
                pnlMain.revalidate();
        		pnlMain.repaint();
        		activePan = clientPan;
        	}
        });
        navBar.add(clients);
        
//      ==================================== affichage de donnees =====================
        //=============================================================================
        activePan = commandePan;
        pnlMain.add(activePan, BorderLayout.CENTER );

//      ================= copy rights ================================
        pnlMain.add( new JLabel( "application realiser par Mohammed Ahbari" ), BorderLayout.SOUTH );
		
		this.setVisible(true);
	}
	
	JToolBar createToolBar(JToolBar toolbar) {
		toolbar.setLayout(new FlowLayout(2,20,0));
		JButton button_1 = new JButton( "+ article" );
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new AddArticleWin();
			}
		});
		toolbar.add( button_1 );
        JButton button = new JButton( "+ client" );
        button.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new AddClientWin();
        	}
        });
        toolbar.add( button );
        JButton button_2 = new JButton( "+ commande" );
        button_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		new AddCommande();
        	}
        });
        toolbar.add( button_2 );
		return toolbar;
		
	}
	
	


}
