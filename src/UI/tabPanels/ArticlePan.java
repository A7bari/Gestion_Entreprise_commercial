package UI.tabPanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import UI.tables.ArticleTable;
import javax.swing.JLabel;

public class ArticlePan extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2624086262985116502L;
	ArticleTable table = new ArticleTable();
	
	public ArticlePan() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scTable = new JScrollPane((Component) table);
		this.add(scTable);
		
        
        JPanel panel = new JPanel();
        panel.setBounds(new Rectangle(17, 10, 19, 36));
        this.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 14, 5));
        
        JButton supp = new JButton("supprimer ");
        supp.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(table.getSelectedRow() == -1) {
        			JOptionPane.showMessageDialog(null, "selectionner une article ");
        		}
        		else if (JOptionPane.showConfirmDialog(null, "vous etes sur ?") == 0) {
        			table.supprimer();
				}
        	}
        });
        
        JLabel lblNewLabel = new JLabel("Gestion d'article :                                  ");
        panel.add(lblNewLabel);
        panel.add(supp);
        
        JButton appliquerModif = new JButton("appliquer les modifications");
        appliquerModif.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(table.getSelectedRow() == -1) {
        			JOptionPane.showMessageDialog(null, "modifier dans le tableau, puis garder l'article selectionner ");
        		} else {
        			table.modifier();        			
        		}
        	}
        });
        panel.add(appliquerModif);
        
        JButton actualiser = new JButton("actualiser");
        actualiser.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		table.actualiser();
        	}
        });
        panel.add(actualiser);
	}
}
