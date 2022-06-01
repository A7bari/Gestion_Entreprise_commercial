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
import UI.CommandeDetails;
import UI.ModifierCommande;
import UI.tables.CommandeTable;
import module.Commande;
import javax.swing.JLabel;

public class CommandePan extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -340583800753142667L;
	
	CommandeTable table = new CommandeTable();
	
	public CommandePan() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scTable = new JScrollPane((Component) table);
		this.add(scTable);
		
        
        JPanel panel = new JPanel();
        panel.setBounds(new Rectangle(17, 10, 19, 36));
        this.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 14, 5));
        
        JButton Modif = new JButton("modifier l'etat");
        Modif.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		Commande cmd = table.getSelecteCmd();
        		if (cmd != null) {
        			new ModifierCommande(cmd);					
				}
        		else 
        		{
        			JOptionPane.showMessageDialog(null,"selectionner une commande dans le tableau");
        		}
        	}
        });
        
        JButton btnNewButton_2 = new JButton("cmds livrees");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		table.cmdLiv();
        	}
        });
        
        JLabel lblNewLabel = new JLabel("Gestion de Commande :          ");
        panel.add(lblNewLabel);
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_1 = new JButton("cmds confirmees");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		table.cmdConf();
        	}
        });
        panel.add(btnNewButton_1);
        
        JButton btnNewButton = new JButton("cmds en attend");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		table.cmdEnAtt();
        	}
        });
        panel.add(btnNewButton);
        panel.add(Modif);
        
        JButton actualiser = new JButton("actualiser");
        actualiser.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		table.actualiser();
        	}
        });
        panel.add(actualiser);
        
        JButton detailsb = new JButton("details");
        detailsb.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int id = table.getSelectedID();
        		new CommandeDetails(id);
        	}
        });
        panel.add(detailsb);
	}
}
