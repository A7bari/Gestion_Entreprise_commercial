package module;

import java.util.ArrayList;

public class Commande {

	int idCommande ,	montant;
	boolean regler ,
			enAttend,	
			confirmer,	
			livrer;
	
	String dateLiv ;
	
	int client ;
	ArrayList<LigneCommande> lignesCommandes ;
	//idCommande,	montant, regler, client,	enAttend, confermer, livrer, dateLiv
	public Commande(int id, 
					int montant, 
					boolean regler, 
					int client, 
					boolean enAttend, 
					boolean confermer, 
					boolean livrer,
					String dateLiv, 
					ArrayList<LigneCommande> lignesCommandes
					) {
		super();
		this.idCommande  = id;
		this.montant = montant;
		this.regler = regler;
		this.enAttend = enAttend;
		this.confirmer = confermer;
		this.livrer = livrer;
		this.dateLiv = dateLiv;
		this.client = client;
		this.lignesCommandes = lignesCommandes;
	}

	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", montant=" + montant + ", regler=" + regler + ", enAttend="
				+ enAttend + ", confermer=" + confirmer + ", livrer=" + livrer + ", dateLiv=" + dateLiv + ", client="
				+ client + ", lignesCommandes=" + lignesCommandes + "]";
	}

	public void setIdCommande(int idCommande) {
		this.idCommande = idCommande;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public void setRegler(boolean regler) {
		this.regler = regler;
	}

	public void setEnAttend(boolean enAttend) {
		this.enAttend = enAttend;
	}

	public void setConfermer(boolean confermer) {
		this.confirmer = confermer;
	}

	public void setLivrer(boolean livrer) {
		this.livrer = livrer;
	}

	public void setDateLiv(String dateLiv) {
		this.dateLiv = dateLiv;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public void setLignesCommandes(ArrayList<LigneCommande> lignesCommandes) {
		this.lignesCommandes = lignesCommandes;
	}

	public int getIdCommande() {
		return idCommande ;
	}

	public int getMontant() {
		return montant;
	}

	public boolean isRegler() {
		return regler;
	}

	public boolean isEnAttend() {
		return enAttend;
	}

	public boolean isConfermer() {
		return confirmer;
	}

	public boolean isLivrer() {
		return livrer;
	}

	public String getDateLiv() {
		return dateLiv;
	}

	public int getClient() {
		return client;
	}

	public ArrayList<LigneCommande> getLignesCommandes() {
		return lignesCommandes;
	}

	public String getEtat() {
		if (confirmer) {
			return "confirmee";
		}else if (enAttend) {
			return "en attend";
		}
			
		return "livree";
	}
	
	
}
