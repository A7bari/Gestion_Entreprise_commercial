package UI.tabPanels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import java.util.ArrayList;


import javax.swing.JPanel;
import javax.swing.JScrollPane;

import UI.tables.LigneCommandeTable;
import module.LigneCommande;


public class LigneCommandePan extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8373985902827900402L;
	
	LigneCommandeTable table ;
	
	public LigneCommandePan() {
		table = new LigneCommandeTable();
		
		setLayout(new BorderLayout(0, 0));
		this.setSize(524, 700);
		JScrollPane scTable = new JScrollPane((Component) table);
		this.add(scTable);
		
        
        JPanel panel = new JPanel();
        panel.setBounds(new Rectangle(17, 10, 19, 36));
        this.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 14, 5));
        
	}
	public LigneCommandePan(ArrayList<LigneCommande> lignCmdList) {
		table = new LigneCommandeTable(lignCmdList);
		setLayout(new BorderLayout(0, 0));
		this.setSize(524, 700);
		JScrollPane scTable = new JScrollPane((Component) table);
		this.add(scTable);
		
        
        JPanel panel = new JPanel();
        panel.setBounds(new Rectangle(17, 10, 19, 36));
        this.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 14, 5));
        
	}
	
	public void actualiserTable(ArrayList<LigneCommande> lst) {
		table.setLigneCommandes(lst);
	}
	
	public void desableTable() {
		table.desable();
	}
}
